package com.taehi.springfirst.domain.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryVO {
    int category_id;
    String category_name;
    String category_url;

    public CategoryVO(){ //없으면에러

    }
    public CategoryVO(int category_id, String category_name, String category_url) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_url = category_url;
    }
}
