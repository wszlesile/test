package com.wsz.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    public Map<String, Object> hello(){
        Map<String,Object> result = new HashMap<>();
        result.put("msg","test hello");
        return result;
    }
}
