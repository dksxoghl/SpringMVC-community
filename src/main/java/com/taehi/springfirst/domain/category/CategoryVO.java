package com.taehi.springfirst.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryVO {
    int categoryId;
    String categoryName;
    String categoryUrl;
    String categoryColor;
    public CategoryVO(){ //없으면에러
    }
}
