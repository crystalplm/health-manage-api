package com.plm.health.manage.api.db.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface MedicalDeptDao {

    ArrayList<HashMap> searchAll();

    ArrayList<HashMap> searchDeptAndSub();

}




