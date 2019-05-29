package com.xywei.redission.Test;


import com.xywei.redission.redission.DistributedLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("redission")
public class TestController {


    @Autowired
    private DistributedLocker locker;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/test1")
    public void test1() {
        String lockKey = "lockKey";
        locker.tryLock(lockKey, TimeUnit.MILLISECONDS, 500, 1000);
        for (int i = 0; i < 10000; i++) {
            if (i==0){
                System.out.println(System.currentTimeMillis());
            }
            String key = "test";
            String value = redisTemplate.opsForValue().get(key);
            value = Optional.ofNullable(value).orElse("0");
            redisTemplate.opsForValue().set(key, String.valueOf(Integer.valueOf(value) + 1));

            if (i==10000){
                System.out.println(System.currentTimeMillis());
            }
        }
    }


    @RequestMapping("/test2")
    public void test2() {
        String lockKey = "lockKey";
        locker.tryLock(lockKey, TimeUnit.MILLISECONDS, 500, 1000);
        for (int i = 0; i < 10000; i++) {
            if (i==0){
                System.out.println(System.currentTimeMillis());
            }
            String key = "test";
            String value = redisTemplate.opsForValue().get(key);
            value = Optional.ofNullable(value).orElse("0");
            redisTemplate.opsForValue().set(key, String.valueOf(Integer.valueOf(value) + 1));
            if (i==10000){
                System.out.println(System.currentTimeMillis());
            }
        }
    }
}
