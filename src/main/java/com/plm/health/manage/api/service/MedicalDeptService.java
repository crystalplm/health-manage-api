package com.plm.health.manage.api.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface MedicalDeptService {
    ArrayList<HashMap> searchAll();

    HashMap searchDeptAndSub();
}
