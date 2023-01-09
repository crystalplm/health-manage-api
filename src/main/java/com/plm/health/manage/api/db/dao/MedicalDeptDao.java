package com.plm.health.manage.api.db.dao;

import com.plm.health.manage.api.db.pojo.MedicalDeptEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MedicalDeptDao {

    ArrayList<HashMap> searchAll();

    ArrayList<HashMap> searchDeptAndSub();

    ArrayList<HashMap> searchByPage(Map param);

    long searchCount(Map param);

    void insert(MedicalDeptEntity entity);

    public HashMap searchById(int id);

    public void update(MedicalDeptEntity entity);

    public long searchSubCount(Integer[] ids);

    public void deleteByIds(Integer[] ids);
}




