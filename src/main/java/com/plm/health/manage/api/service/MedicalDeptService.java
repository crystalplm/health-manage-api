package com.plm.health.manage.api.service;

import com.plm.health.manage.api.common.PageUtils;
import com.plm.health.manage.api.db.pojo.MedicalDeptEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MedicalDeptService {
    ArrayList<HashMap> searchAll();

    HashMap searchDeptAndSub();

    PageUtils searchByPage(Map param);

    void insert(MedicalDeptEntity entity);

    public HashMap searchById(int id);

    public void update(MedicalDeptEntity entity);

    public void deleteByIds(Integer[] ids);
}
