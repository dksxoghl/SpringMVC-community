CREATE TABLE IF NOT EXISTS hboard_tb
(
    hy_id Serial NOT NULL,
-- h_no Integer Not NULL Default (ROW_NUMBER() OVER()),
    category_id Integer NOT NULL DEFAULT 1,
    hy_subject VARCHAR(30) NOT NULL,
    hy_content varchar NOT NULL,
    hy_created_date DATE NOT NULL default CURRENT_TIMESTAMP,
    user_id VARCHAR(10) NOT NULL,
    hy_hit INTEGER DEFAULT 0 NOT NULL,
-- hy_like INTEGER DEFAULT 0 NOT NULL,
    hy_url VARCHAR NOT NULL,
    file_name VARCHAR,
    hy_img VARCHAR,
    PRIMARY KEY(hy_id)
);
update Hboard_TB set hy_hit=hy_hit+1 where hy_id=140;
ALTER TABLE hboard_tb drop column hy_like;
alter table hboard_tb add h_no Integer Not NULL Default (ROW_NUMBER() OVER())

    - drop table hboard_tb cascade;

SELECT (ROW_NUMBER() OVER()) AS h_no
     , *
FROM hboard_tb;

--리스트3조인
select (ROW_NUMBER() OVER(order by Hboard_TB.hy_id)) AS hy_no,Hboard_TB.*,rep,hy_like
from Hboard_TB left outer join (select count(*) as rep, hy_id from reply_tb group by hy_id) B
                               on Hboard_TB.hy_id= B.hy_id
               left outer join (select count(*) as hy_like,hy_id from like_tb group by hy_id) C
                               on Hboard_TB.hy_id= C.hy_id
where category_id=(select category_id from category_tb where category_url='hy')
-- and hy_like>=0
order by Hboard_TB.hy_id desc limit 10 offset (1 - 1) * 10;



—리스트
select (ROW_NUMBER() OVER(order by Hboard_TB.hy_id)) AS hy_no,Hboard_TB.*,rep
from Hboard_TB left outer join (select count(*) as rep, hy_id from reply_tb group by hy_id) B
                               on Hboard_TB.hy_id= B.hy_id
where category_id=(select category_id from category_tb where category_url='hy')
order by Hboard_TB.hy_id desc limit 10 offset (1 - 1) * 10;

select (ROW_NUMBER() OVER(order by h_id)) AS h_no
     ,* from Hboard_TB where category_id=1
order by h_id desc limit 10 offset (1 - 1) * 10;
select * from Hboard_TB where category_id=(
    select category_id from category_tb where category_url='ch')
order by h_id desc limit 10 offset (1 - 1) * 10;

select count(*) from Hboard_TB where category_id=(
    select category_id from category_tb where category_url='ja');

insert into Hboard_TB(category_id,hy_subject,hy_content,user_id,hy_url)
values (1,'aaa','실험','ㅌㅎ','www.asfd');
insert into Hboard_TB(category_id,hy_subject,hy_content,user_id,hy_url)
values (1,'bbb','실험2','ㅌㅎ2','www.asfd');
insert into Hboard_TB(category_id,hy_subject,hy_content,user_id,hy_url)
values (2,'222','실험2','ㅌㅎ','[www.ch](http://www.ch/)');
insert into Hboard_TB(category_id,hy_subject,hy_content,user_id,hy_url)
values (3,'333','실험2','ㅌㅎ','www.ja');

insert into user_tb(user_id,password,adult)
values ('ㅌㅎ','asd',true);
insert into user_tb(user_id,password,adult)
values ('ㅌㅎ2','asd',false);
insert into category_tb(category_id,category_name,category_url)
values (1,'해외연예','hy');
insert into category_tb(category_id,category_name,category_url)
values (2,'중국연예','ch');
insert into category_tb(category_id,category_name,category_url)
values (3,'일본연예','ja');
insert into category_tb(category_id,category_name,category_url)
values (4,'게임','ga');
insert into category_tb(category_id,category_name,category_url)
values (5,'스포츠','sp');
insert into category_tb(category_id,category_name,category_url)
values (6,'라이프','li');
insert into category_tb(category_id,category_name,category_url,category_color)
values (7,'공지','nt','#3a3a3a');
-- drop table hboard_tb cascade;
CREATE TABLE IF NOT EXISTS category_tb
(
    category_id SMALLINT NOT NULL,
    category_name VARCHAR(10) NOT NULL,
    category_url varchar,
    category_color varchar,
    PRIMARY KEY(category_id)
);
select * from category_tb;
ALTER TABLE category_tb alter column category_id type integer;

CREATE TABLE IF NOT EXISTS user_tb
(
    user_id VARCHAR(10) NOT NULL,
    password VARCHAR(20) NOT NULL,
    adult BOOLEAN ,
    email varchar(20) ,
    PRIMARY KEY(user_id)
);

ALTER TABLE user_tb alter column password type varchar(100);
select * from user_tb;

CREATE TABLE IF NOT EXISTS like_tb
(
    like_no Serial NOT NULL,
    user_id VARCHAR(10),
    hy_id INTEGER,
    PRIMARY KEY(like_no)
);
select count(*) from like_tb where hy_id=181 and user_id='asd';
insert into like_tb(user_id,hy_id) values('asd',182);
ALTER TABLE like_tb rename column h_id to hy_id;

CREATE TABLE IF NOT EXISTS reply_tb
(
    re_id INTEGER NOT NULL,
    hy_id INTEGER NOT NULL,
    user_id VARCHAR(10) NOT null,
    re_regdate DATE NOT NULL default CURRENT_TIMESTAMP, --without timezone? 재설정
    re_content VARCHAR(100),
    re_group integer,
    re_order integer default 0,
    re_indent integer default 0,
    re_parent integer default 0
        PRIMARY KEY(re_id)
);
ALTER TABLE reply_tb alter column re_order set default 0;
create SEQUENCE seq_reply START 1;
drop SEQUENCE seq_reply;
drop table reply_tb;
insert into reply_tb(re_id,hy_id,user_id,re_content,re_group,re_order,re_indent,re_parent)
values (nextval('seq_reply'),138,'ㅌㅎ','1',currval('seq_reply'),5,1,1);
select * from reply_tb where hy_id=138;
delete from reply_tb where hy_id=138 and re_id=12;

select * from reply_tb where hy_id=175 order by re_group,re_order limit 10 offset (1 - 1) * 10;
update reply_tb set re_order=re_order+1 where re_group=1 and re_order>=4;
update reply_tb set re_content='[작성자가 삭제한 댓글입니다.]' where re_id=1;
select re_id from reply_tb where re_parent=4;

CREATE TABLE IF NOT EXISTS img_tb
(

);
CREATE TABLE IF NOT EXISTS file_tb
(
    file_name VARCHAR NOT NULL,
    file_size INTEGER,
    file_type VARCHAR,
    file_date DATE,
    hy_id INTEGER,
    PRIMARY KEY(file_name)
);
ALTER TABLE file_tb rename column h_id to hy_id;
drop table file_tb;

- Create FKs
ALTER TABLE hboard_tb
    ADD FOREIGN KEY (category_id)
        REFERENCES category_tb(category_id)
            MATCH SIMPLE
;

ALTER TABLE hboard_tb
    ADD FOREIGN KEY (user_id)
        REFERENCES user_tb(user_id)
            MATCH SIMPLE
;

ALTER TABLE like_tb
    ADD FOREIGN KEY (user_id)
        REFERENCES user_tb(user_id)
            MATCH SIMPLE
;

ALTER TABLE like_tb
    ADD FOREIGN KEY (hy_id)
        REFERENCES hboard_tb(hy_id)
            MATCH SIMPLE
;

ALTER TABLE reply_tb
    ADD FOREIGN KEY (user_id)
        REFERENCES user_tb(user_id)
            MATCH SIMPLE
;

ALTER TABLE reply_tb
    ADD FOREIGN KEY (hy_id)
        REFERENCES hboard_tb(hy_id)
            MATCH SIMPLE
;

ALTER TABLE file_tb
    ADD FOREIGN KEY (hy_id)
        REFERENCES hboard_tb(hy_id)
            MATCH SIMPLE
;