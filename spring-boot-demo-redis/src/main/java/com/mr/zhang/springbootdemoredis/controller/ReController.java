package com.mr.zhang.springbootdemoredis.controller;


import java.util.concurrent.TimeUnit;

import com.mr.zhang.springbootdemoredis.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ReController {


    public static final Logger log = LoggerFactory.getLogger(ReController.class);

    @Autowired
    private RedisUtil redisUtil;
    @RequestMapping(value = "/hello")
    public String hello(String id){
        //查询缓存中是否存在
        boolean hasKey = redisUtil.exists("llll");
        String str = "";
        if(hasKey){
            //获取缓存
            Object object =  redisUtil.get("llll");
            log.info("从缓存获取的数据"+ object);
            str = object.toString();
        }else{
            //从数据库中获取信息
            log.info("从数据库中获取数据");
            //str = testService.test();
            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtil.set("llll",str,10L,TimeUnit.MINUTES);
            log.info("数据插入缓存" + str);
        }
        return str;
    }
}
