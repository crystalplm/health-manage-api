package com.plm.health.manage.api.db.dao;

import com.plm.health.manage.api.db.pojo.DoctorEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DoctorDao {
    ArrayList<HashMap> searchByPage(Map param);

    long searchCount(Map param);

    HashMap searchContent(int id);

    void updatePhoto(Map param);

    @Transactional
    void insert(DoctorEntity entity);

    Integer searchIdByUuid(String uuid);

    HashMap searchById(int id);
    void update(Map param);

    void deleteByIds(Integer[] ids);
}




