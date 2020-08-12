package com.taehi.springfirst.domain.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingVO {
            // 현재페이지, 시작페이지, 끝페이지, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지,
    private int nowPage, startPage, endPage, total, cntPerPage, lastPage;
    //밑에 페이지번호 보여지는갯수
    private int cntPage = 7;
    public PagingVO() {
    }
    public PagingVO(int total, int nowPage, int cntPerPage) {
        setNowPage(nowPage);
        setCntPerPage(cntPerPage);
        setTotal(total);
        calcLastPage(getTotal(), getCntPerPage());
        calcStartEndPage(getNowPage(), cntPage);
    }
    // 제일 마지막 페이지 계산     25/10 > 3페이지마지막
    public void calcLastPage(int total, int cntPerPage) {
        setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
    }
    // 시작, 끝 페이지 계산    6/7
    public void calcStartEndPage(int nowPage, int cntPage) {
        //+3 하자
        if(nowPage<4) setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
        else setEndPage(nowPage+3);
        //보여지는 페이지 1 ..7 일때 글이 5까지밖에없을때 마지막페이지5로강제설정
        if (getLastPage() < getEndPage()) {
            setEndPage(getLastPage());
        }
        setStartPage(getEndPage() - cntPage + 1);
        if (getStartPage() < 1) {
            setStartPage(1);
        }
    }

}
