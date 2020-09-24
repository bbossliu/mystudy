package com.mystudy.lx.entity.Do;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author dalaoban
 * @create 2020-05-11-22:59
 */
@Data
@Accessors(chain = true)
public class TtlProductInfoDo {


    private Long id;
    private String productName;
    private Long categoryId;
    private String categoryName;
    private Long branchId;
    private String branchName;
    private Long shopId;
    private String shopName;
    private Double price;
    private Integer stock;
    private Integer salesNum;
    private String createTime;
    private String updateTime;
    private Byte isDel;

}
