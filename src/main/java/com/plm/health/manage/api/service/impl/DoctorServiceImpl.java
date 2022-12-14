package com.plm.health.manage.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.plm.health.manage.api.common.PageUtils;
import com.plm.health.manage.api.db.dao.DoctorDao;
import com.plm.health.manage.api.db.dao.MedicalDeptSubAndDoctorDao;
import com.plm.health.manage.api.db.pojo.DoctorEntity;
import com.plm.health.manage.api.db.pojo.MedicalDeptSubAndDoctorEntity;
import com.plm.health.manage.api.exception.HospitalException;
import com.plm.health.manage.api.service.DoctorService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author crystal
 */
@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorDao doctorDao;

    @Resource
    private MedicalDeptSubAndDoctorDao medicalDeptSubAndDoctorDao;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Override
    public PageUtils searchByPage(Map param) {
        ArrayList<HashMap> list = null;
        long count = doctorDao.searchCount(param);
        if (count > 0) {
            list = doctorDao.searchByPage(param);
        } else {
            list = new ArrayList<>();
        }
        int page = MapUtil.getInt(param, "page");
        int length = MapUtil.getInt(param, "length");
        PageUtils pageUtils = new PageUtils(list, count, page, length);
        return pageUtils;
    }

    @Override
    public HashMap searchContent(int id) {
        HashMap map = doctorDao.searchContent(id);
        JSONArray tag = JSONUtil.parseArray(MapUtil.getStr(map, "tag"));
        map.replace("tag", tag);
        return map;
    }

    @Override
    @Transactional
    public void updatePhoto(MultipartFile file, Integer doctorId) {
        try {
            String filename = "doctor-" + doctorId + ".jpg";
            //???Minio?????????????????????
            MinioClient client = new MinioClient.Builder().endpoint(endpoint)
                    .credentials(accessKey, secretKey).build();

            client.putObject(PutObjectArgs.builder().bucket("hospital")
                    .object("doctor/" + filename)
                    .stream(file.getInputStream(), -1, 5 * 1024 * 1024)
                    .contentType("image/jpeg").build());

            //???????????????photo??????
            doctorDao.updatePhoto(new HashMap() {{
                put("id", doctorId);
                put("photo", "/doctor/" + filename);
            }});
        } catch (Exception e) {
            log.error("????????????????????????", e);
            throw new HospitalException("????????????????????????");
        }
    }

    @Override
    public void insert(Map param) {
        //??????????????????
        DoctorEntity entity_1 = BeanUtil.toBean(param, DoctorEntity.class);
        doctorDao.insert(entity_1);

        //??????uuid?????????????????????
        String uuid = entity_1.getUuid();
        Integer doctorId = doctorDao.searchIdByUuid(uuid);

        //????????????????????????
        int subId = MapUtil.getInt(param, "subId");
        MedicalDeptSubAndDoctorEntity entity_2 = new MedicalDeptSubAndDoctorEntity();
        entity_2.setDeptSubId(subId);
        entity_2.setDoctorId(doctorId);
        medicalDeptSubAndDoctorDao.insert(entity_2);
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = doctorDao.searchById(id);
        String tag = MapUtil.getStr(map, "tag");
        JSONArray array = JSONUtil.parseArray(tag);
        map.replace("tag", array);
        return map;
    }

    @Override
    @Transactional
    public void update(Map param) {
        doctorDao.update(param);
        param = MapUtil.renameKey(param, "id", "doctorId");
        medicalDeptSubAndDoctorDao.updateDoctorSubDept(param);
    }

    @Override
    @Transactional
    public void deleteByIds(Integer[] ids) {
        doctorDao.deleteByIds(ids);
    }
}
