package com.tutorial.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * @author jimmy
 * @date 2019-09-11 22:55
 */
//@Configuration
@Slf4j
public class ScheduleConfigurer implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    /**
     * 这里设置的线程池的corePoolSize很关键
     * 假设有4个任务需要每隔1秒执行,其中三个都是比较耗时的操作可能需要10多秒,
     * 如果Executors.newScheduledThreadPool(3),那么仍然可能导致最后一个任务被阻塞不能定时执行
     * @return
     */
    @Bean
    public Executor taskExecutor(){
        // Spring提供的定时任务线程池类
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        //设定最大可用的线程数目
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }

    //动态设置
  /*  @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Method[] methods = BatchProperties.Job.class.getMethods();
        int defaultPoolSize = 3;
        int corePoolSize = 0;
        if (methods != null && methods.length > 0) {
            for (Method method : methods) {
                Scheduled annotation = method.getAnnotation(Scheduled.class);
                if (annotation != null) {
                    corePoolSize++;
                }
            }
            if (defaultPoolSize > corePoolSize)
                corePoolSize = defaultPoolSize;
        }
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(corePoolSize));
    }*/

}
