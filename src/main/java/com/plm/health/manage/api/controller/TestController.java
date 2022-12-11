package com.plm.health.manage.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author crystal
 */
@RestController
public class TestController {

    @RequestMapping
    public String test() {
        System.out.println("test----");
        return "本地测试";
    }
}
