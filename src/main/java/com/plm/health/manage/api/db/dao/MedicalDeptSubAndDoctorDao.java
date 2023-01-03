package com.plm.health.manage.api.db.dao;

import com.plm.health.manage.api.db.pojo.MedicalDeptSubAndDoctorEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface MedicalDeptSubAndDoctorDao {
    @Transactional
    void insert(MedicalDeptSubAndDoctorEntity entity);

    void updateDoctorSubDept(Map param);
}




