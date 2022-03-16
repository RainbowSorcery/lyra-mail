package com.lyra.mail.product.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.entity.vo.Catalog2VO;
import com.lyra.mail.product.service.IPmsCategoryService;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class IndexController {
    @Autowired
    private IPmsCategoryService categoryService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping(value = {"/index.html", "/"})
    public String index(Model model) {
        List<PmsCategory> categories = categoryService.findCategoryByFirstCategory();
        model.addAttribute("categoryList", categories);

        return "index";
    }

    @GetMapping("index/catalog.json")
    @ResponseBody
    public Map<String, List<Catalog2VO>> getCatalogJson() {

        try {
            return categoryService.getCatalogJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        // 获取一啊锁
        RLock lock = redissonClient.getLock("my-lock");

        lock.lock();
        System.out.println("加锁成功--------" + Thread.currentThread().getId());

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁成功--------" + Thread.currentThread().getId());
            lock.unlock();
        }

        return "Hello world";
    }

    @GetMapping("/read")
    @ResponseBody
    public String read() {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rd-lock");
        // 获取读锁
        RLock rLock = readWriteLock.readLock();

        String s = null;

        try {
            rLock.lock();
            System.out.println("read 上锁");
            s = redisTemplate.opsForValue().get("writeValue");
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("read 解锁");
            rLock.unlock();
        }

        return s;
    }

    @GetMapping("/write")
    @ResponseBody
    public String write() {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("rd-lock");

        // 获取写锁
        RLock writeLock = readWriteLock.writeLock();

        String s = UUID.randomUUID().toString();

        try {
            writeLock.lock();
            System.out.println("write 上锁");
            redisTemplate.opsForValue().set("writeValue", s);
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("write 解锁");
            writeLock.unlock();
        }

        return s;
    }

    @GetMapping("/p")
    @ResponseBody
    public String p() {
        RSemaphore pv = redissonClient.getSemaphore("pv");

        try {
            pv.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @GetMapping("/v")
    @ResponseBody
    public String v() {
        RSemaphore pv = redissonClient.getSemaphore("pv");

        pv.release();

        return "ok";
    }

    // 闭锁 所有操作完成才能进行闭锁操作
    @GetMapping("/lockDoor")
    @ResponseBody
    public String lockDoor() throws InterruptedException {
        // 获取一个闭锁
        RCountDownLatch downLock = redissonClient.getCountDownLatch("downLock");
        // 设置计数器的值
        downLock.trySetCount(5);

        downLock.await();

        return "lock door";
    }

    @GetMapping("/gogogo")
    @ResponseBody
    public String go() {
        RCountDownLatch downLock = redissonClient.getCountDownLatch("downLock");
        // 计数器 - 1
        downLock.countDown();

        return "go";
    }
}
