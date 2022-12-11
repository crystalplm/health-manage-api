package com.plm.health.manage.api.db.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DoctorPriceEntity {
    private Integer id;
    private Integer doctorId;
    private String level;
    private BigDecimal price_1;
    private BigDecimal price_2;

}