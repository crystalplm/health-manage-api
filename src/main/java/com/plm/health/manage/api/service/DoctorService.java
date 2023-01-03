package com.plm.health.manage.api.service;

import com.plm.health.manage.api.common.PageUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

public interface DoctorService {
    PageUtils searchByPage(Map param);

    HashMap searchContent(int id);

    void updatePhoto(MultipartFile file, Integer doctorId);

    void insert(Map param);

    HashMap searchById(int id);

    void update(Map param);

    void deleteByIds(Integer[] ids);
}
