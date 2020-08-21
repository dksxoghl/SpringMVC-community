package com.taehi.springfirst.domain.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryVO {
    int categoryId;
    String categoryName;
    String categoryUrl;

    public CategoryVO(){ //없으면에러

    }

    public CategoryVO(int categoryId, String categoryName, String categoryUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryUrl = categoryUrl;
    }
}
