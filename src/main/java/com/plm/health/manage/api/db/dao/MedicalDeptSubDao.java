package com.plm.health.manage.api.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MedicalDeptSubDao {

    public ArrayList<HashMap> searchByPage(Map param);

    public long searchCount(Map param);

}




