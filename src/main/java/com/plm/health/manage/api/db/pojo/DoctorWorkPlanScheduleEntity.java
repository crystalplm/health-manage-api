package com.plm.health.manage.api.db.pojo;

import lombok.Data;

@Data
public class DoctorWorkPlanScheduleEntity {
    private Integer id;
    private Integer workPlanId;
    private Integer slot;
    private Integer maximum;
    private Integer num;

}