package com.example.jingdong.form;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductForm {

    private Integer id;

    @NotNull(message = "商品名称不可为空")
    private String name;

    @NotNull(message = "商品销售价不可为空")
    @DecimalMin(value = "0.1",message = "销售价不可小于0.1")
    private BigDecimal price;

    @NotNull(message = "商品原价不可为空")
    @DecimalMin(value = "0.1",message = "原价不可小于0.1")
    private BigDecimal oldPrice;

    @NotNull(message = "商品库存不可为空")
    @Min(value = 0,message = "库存不可小于0")
    private Integer stock;

    @NotNull(message = "商品类型不可为空")
    private String type;

    private Integer state;

}
