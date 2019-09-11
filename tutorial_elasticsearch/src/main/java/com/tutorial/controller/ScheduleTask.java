package com.tutorial.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务
 * @author jimmy
 * @date 2019-09-11 22:22
 */
@Component
@Slf4j
public class ScheduleTask {

    //@Async
    @Scheduled(cron = "*/2 * * * * ?")
    public void task1() throws InterruptedException {
        log.info("我是task—1,我的线程的 id == > {},时间 == >{}", Thread.currentThread().getId(), new Date());
        Thread.sleep(1000*5);
        log.info("task-1 ending ,我的线程的 id == > {} ,时间 == > {}", Thread.currentThread().getId(), new Date());
    }

    //@Async
    @Scheduled(cron = "*/2 * * * * ?")
    public void task2() throws InterruptedException {
        log.info("我是task-2,我的线程的 id == > {},时间 == >{}", Thread.currentThread().getId(), new Date());
      //  Thread.sleep(2000);
      //  log.info("task-2 ending,我的线程的 id == > {} ,时间 == > {}", Thread.currentThread().getId(), new Date());
    }

    //@Scheduled(cron = "*/2 * * * * ?")
    public void task3() throws InterruptedException {
        log.info("我是task-3,我的线程的 id == > {},时间 == >{}", Thread.currentThread().getId(), new Date());
        int i = 1/0;
    }
}
