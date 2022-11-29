package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductCategoryForm {

    private Integer id;

    @NotNull(message = "分类名称不可为空")
    private String name;

    @NotNull(message = "分类类型不可为空")
    private String type;

    private Integer state;

}
