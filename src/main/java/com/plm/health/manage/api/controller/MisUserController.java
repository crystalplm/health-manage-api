package com.plm.health.manage.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.plm.health.manage.api.common.R;
import com.plm.health.manage.api.controller.form.LoginForm;
import com.plm.health.manage.api.service.MisUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author crystal
 */
@Validated
@RestController
@RequestMapping("/mis_user")
public class MisUserController {

    @Resource
    private MisUserService misUserService;

    @PostMapping("/login")
    public R login(@RequestBody @Valid LoginForm form) {
        Map param = BeanUtil.beanToMap(form);
        Integer userId = misUserService.login(param);
        if (userId != null) {
            StpUtil.login(userId);
            String token = StpUtil.getTokenValue();
            List<String> permissions = StpUtil.getPermissionList();
            return R.ok().put("result", true).put("token", token)
                    .put("permissions", permissions);

        }
        return R.ok().put("result", false);

    }

    @GetMapping("/logout")
    @SaCheckLogin
    public R logout() {
        StpUtil.logout();
        return R.ok();
    }
}
