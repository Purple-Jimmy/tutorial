# schedule定时任务

## 使用 在启动类上添加@EnableScheduling注解

## 定时任务的同步执行

### 任务1和任务2都不阻塞
```
@Component
@Slf4j
public class ScheduleTask {

    @Scheduled(cron = "*/2 * * * * ?")
    public void task1() throws InterruptedException {
        log.info("我是task—1,我的线程的 id == > {},时间 == >{}", Thread.currentThread().getId(), new Date());
    }
    
    
    @Scheduled(cron = "*/2 * * * * ?")
    public void task2() throws InterruptedException {
        log.info("我是task-2,我的线程的 id == > {},时间 == >{}", Thread.currentThread().getId(), new Date());
    }
}
```
打印如下:在任务1和任务2都不存在阻塞的情况下,任务1和任务2都在同一个点执行
```
2019-09-11 22:30:04.007 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:30:04.006+0800
2019-09-11 22:30:04.009 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [26] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:30:04.009+0800
2019-09-11 22:30:06.005 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:30:06.005+0800
2019-09-11 22:30:06.006 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [26] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:30:06.006+0800
2019-09-11 22:30:08.002 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [26] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:30:08.002+0800
2019-09-11 22:30:08.003 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:30:08.003+0800
2019-09-11 22:30:10.005 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [26] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:30:10.005+0800
2019-09-11 22:30:10.006 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:30:10.006+0800
2019-09-11 22:30:12.005 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:30:12.005+0800
2019-09-11 22:30:12.006 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [26] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:30:12.006+0800
2019-09-11 22:30:14.005 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:30:14.005+0800
2019-09-11 22:30:14.008 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [26] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:30:14.008+0800
```


### 任务1阻塞执行
```
@Scheduled(cron = "*/2 * * * * ?")
public void task1() throws InterruptedException {
    log.info("我是task—1,我的线程的 id == > {},时间 == >{}", Thread.currentThread().getId(), new Date());
    Thread.sleep(1000*5);
    log.info("task-1 ending ,我的线程的 id == > {} ,时间 == > {}", Thread.currentThread().getId(), new Date());
}
```
打印如下:任务2在任务1执行耗时5s之后再执行
```
2019-09-11 22:37:40.008 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:37:40.006+0800
2019-09-11 22:37:45.009 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 29 ,时间 == > 2019-09-11T22:37:45.009+0800
2019-09-11 22:37:45.010 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [27] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:37:45.010+0800
2019-09-11 22:37:46.000 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [27] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:37:46.000+0800
2019-09-11 22:37:46.001 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:37:46.001+0800
2019-09-11 22:37:51.002 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 29 ,时间 == > 2019-09-11T22:37:51.002+0800
2019-09-11 22:37:51.003 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [27] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:37:51.003+0800
2019-09-11 22:37:52.003 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [27] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:37:52.003+0800
2019-09-11 22:37:52.004 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:37:52.004+0800
2019-09-11 22:37:57.007 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 29 ,时间 == > 2019-09-11T22:37:57.007+0800
2019-09-11 22:37:57.008 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [27] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:37:57.008+0800
2019-09-11 22:37:58.003 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:37:58.003+0800
2019-09-11 22:38:03.009 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 29 ,时间 == > 2019-09-11T22:38:03.009+0800
2019-09-11 22:38:03.010 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [27] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:38:03.010+0800
2019-09-11 22:38:04.000 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [27] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:38:04.000+0800
2019-09-11 22:38:04.001 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [20] [ LOGID: ]-| 我是task—1,我的线程的 id == > 29,时间 == >2019-09-11T22:38:04.001+0800
2019-09-11 22:38:09.006 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 29 ,时间 == > 2019-09-11T22:38:09.006+0800
2019-09-11 22:38:09.007 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [27] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:38:09.007+0800
2019-09-11 22:38:10.004 |-INFO  [scheduling-1] com.tutorial.controller.ScheduleTask [27] [ LOGID: ]-| 我是task-2,我的线程的 id == > 29,时间 == >2019-09-11T22:38:10.004+0800
```

综上可以看出schedule默认是单线程执行的

## 定时任务的异步执行

### @Async注解实现
```
@Async
@Scheduled(cron = "*/2 * * * * ?")
public void task1() throws InterruptedException {
    log.info("我是task—1,我的线程的 id == > {},时间 == >{}", Thread.currentThread().getId(), new Date());
    Thread.sleep(1000*5);
    log.info("task-1 ending ,我的线程的 id == > {} ,时间 == > {}", Thread.currentThread().getId(), new Date());
}

@Async
@Scheduled(cron = "*/2 * * * * ?")
public void task2() throws InterruptedException {
    log.info("我是task-2,我的线程的 id == > {},时间 == >{}", Thread.currentThread().getId(), new Date());
}
```
打印如下:任务1和任务2是并行执行的,但是任务1执行完需要耗时5s,但是也发生了并行,在某些场景比如事务就会有问题
```
2019-09-11 22:50:20.016 |-INFO  [task-1] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 47,时间 == >2019-09-11T22:50:20.015+0800
2019-09-11 22:50:20.016 |-INFO  [task-2] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 48,时间 == >2019-09-11T22:50:20.015+0800
2019-09-11 22:50:22.005 |-INFO  [task-3] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 55,时间 == >2019-09-11T22:50:22.005+0800
2019-09-11 22:50:22.006 |-INFO  [task-4] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 56,时间 == >2019-09-11T22:50:22.006+0800
2019-09-11 22:50:24.005 |-INFO  [task-5] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 57,时间 == >2019-09-11T22:50:24.005+0800
2019-09-11 22:50:24.006 |-INFO  [task-6] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 58,时间 == >2019-09-11T22:50:24.006+0800
2019-09-11 22:50:25.022 |-INFO  [task-2] com.tutorial.controller.ScheduleTask [24] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 48 ,时间 == > 2019-09-11T22:50:25.022+0800
2019-09-11 22:50:26.006 |-INFO  [task-7] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 59,时间 == >2019-09-11T22:50:26.006+0800
2019-09-11 22:50:26.006 |-INFO  [task-8] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 60,时间 == >2019-09-11T22:50:26.006+0800
2019-09-11 22:50:27.011 |-INFO  [task-3] com.tutorial.controller.ScheduleTask [24] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 55 ,时间 == > 2019-09-11T22:50:27.011+0800
2019-09-11 22:50:28.005 |-INFO  [task-1] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 47,时间 == >2019-09-11T22:50:28.005+0800
2019-09-11 22:50:28.005 |-INFO  [task-4] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 56,时间 == >2019-09-11T22:50:28.005+0800
2019-09-11 22:50:29.010 |-INFO  [task-6] com.tutorial.controller.ScheduleTask [24] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 58 ,时间 == > 2019-09-11T22:50:29.010+0800
2019-09-11 22:50:30.002 |-INFO  [task-5] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 57,时间 == >2019-09-11T22:50:30.002+0800
2019-09-11 22:50:30.002 |-INFO  [task-2] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 48,时间 == >2019-09-11T22:50:30.002+0800
2019-09-11 22:50:31.011 |-INFO  [task-7] com.tutorial.controller.ScheduleTask [24] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 59 ,时间 == > 2019-09-11T22:50:31.011+0800
2019-09-11 22:50:32.001 |-INFO  [task-8] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 60,时间 == >2019-09-11T22:50:32.001+0800
2019-09-11 22:50:32.001 |-INFO  [task-3] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 55,时间 == >2019-09-11T22:50:32.001+0800
2019-09-11 22:50:33.007 |-INFO  [task-4] com.tutorial.controller.ScheduleTask [24] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 56 ,时间 == > 2019-09-11T22:50:33.007+0800
2019-09-11 22:50:34.002 |-INFO  [task-1] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 47,时间 == >2019-09-11T22:50:34.002+0800
2019-09-11 22:50:34.002 |-INFO  [task-6] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 58,时间 == >2019-09-11T22:50:34.002+0800
2019-09-11 22:50:35.006 |-INFO  [task-2] com.tutorial.controller.ScheduleTask [24] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 48 ,时间 == > 2019-09-11T22:50:35.005+0800
2019-09-11 22:50:36.004 |-INFO  [task-5] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 57,时间 == >2019-09-11T22:50:36.004+0800
2019-09-11 22:50:36.004 |-INFO  [task-7] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 59,时间 == >2019-09-11T22:50:36.004+0800
2019-09-11 22:50:37.005 |-INFO  [task-3] com.tutorial.controller.ScheduleTask [24] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 55 ,时间 == > 2019-09-11T22:50:37.005+0800
2019-09-11 22:50:38.004 |-INFO  [task-8] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 60,时间 == >2019-09-11T22:50:38.004+0800
2019-09-11 22:50:38.004 |-INFO  [task-4] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 56,时间 == >2019-09-11T22:50:38.004+0800
2019-09-11 22:50:39.006 |-INFO  [task-6] com.tutorial.controller.ScheduleTask [24] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 58 ,时间 == > 2019-09-11T22:50:39.005+0800
2019-09-11 22:50:40.003 |-INFO  [task-1] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 47,时间 == >2019-09-11T22:50:40.003+0800
2019-09-11 22:50:40.003 |-INFO  [task-2] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 48,时间 == >2019-09-11T22:50:40.003+0800
2019-09-11 22:50:41.009 |-INFO  [task-7] com.tutorial.controller.ScheduleTask [24] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 59 ,时间 == > 2019-09-11T22:50:41.009+0800
2019-09-11 22:50:42.006 |-INFO  [task-5] com.tutorial.controller.ScheduleTask [30] [ LOGID: ]-| 我是task-2,我的线程的 id == > 57,时间 == >2019-09-11T22:50:42.005+0800
2019-09-11 22:50:42.006 |-INFO  [task-3] com.tutorial.controller.ScheduleTask [22] [ LOGID: ]-| 我是task—1,我的线程的 id == > 55,时间 == >2019-09-11T22:50:42.006+0800
```

### 使用线程池 新建配置类
```
@Configuration
@Slf4j
public class ScheduleConfigurer implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor(){
        return Executors.newScheduledThreadPool(10);
    }
}
```

打印如下:任务1和任务并行执行,且任务1的下一个任务是在上一个任务执行结束之后再执行
```
2019-09-11 22:59:38.007 |-INFO  [pool-2-thread-2] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 31,时间 == >2019-09-11T22:59:38.005+0800
2019-09-11 22:59:38.007 |-INFO  [pool-2-thread-1] com.tutorial.controller.ScheduleTask [21] [ LOGID: ]-| 我是task—1,我的线程的 id == > 30,时间 == >2019-09-11T22:59:38.005+0800
2019-09-11 22:59:40.006 |-INFO  [pool-2-thread-2] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 31,时间 == >2019-09-11T22:59:40.005+0800
2019-09-11 22:59:42.004 |-INFO  [pool-2-thread-3] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 54,时间 == >2019-09-11T22:59:42.004+0800
2019-09-11 22:59:43.010 |-INFO  [pool-2-thread-1] com.tutorial.controller.ScheduleTask [23] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 30 ,时间 == > 2019-09-11T22:59:43.010+0800
2019-09-11 22:59:44.002 |-INFO  [pool-2-thread-2] com.tutorial.controller.ScheduleTask [21] [ LOGID: ]-| 我是task—1,我的线程的 id == > 31,时间 == >2019-09-11T22:59:44.002+0800
2019-09-11 22:59:44.002 |-INFO  [pool-2-thread-4] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 55,时间 == >2019-09-11T22:59:44.002+0800
2019-09-11 22:59:46.004 |-INFO  [pool-2-thread-3] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 54,时间 == >2019-09-11T22:59:46.004+0800
2019-09-11 22:59:48.004 |-INFO  [pool-2-thread-5] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 56,时间 == >2019-09-11T22:59:48.004+0800
2019-09-11 22:59:49.009 |-INFO  [pool-2-thread-2] com.tutorial.controller.ScheduleTask [23] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 31 ,时间 == > 2019-09-11T22:59:49.009+0800
2019-09-11 22:59:50.005 |-INFO  [pool-2-thread-1] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 30,时间 == >2019-09-11T22:59:50.005+0800
2019-09-11 22:59:50.005 |-INFO  [pool-2-thread-6] com.tutorial.controller.ScheduleTask [21] [ LOGID: ]-| 我是task—1,我的线程的 id == > 57,时间 == >2019-09-11T22:59:50.005+0800
2019-09-11 22:59:52.001 |-INFO  [pool-2-thread-1] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 30,时间 == >2019-09-11T22:59:52.001+0800
2019-09-11 22:59:54.004 |-INFO  [pool-2-thread-1] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 30,时间 == >2019-09-11T22:59:54.004+0800
2019-09-11 22:59:55.007 |-INFO  [pool-2-thread-6] com.tutorial.controller.ScheduleTask [23] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 57 ,时间 == > 2019-09-11T22:59:55.007+0800
2019-09-11 22:59:56.005 |-INFO  [pool-2-thread-1] com.tutorial.controller.ScheduleTask [21] [ LOGID: ]-| 我是task—1,我的线程的 id == > 30,时间 == >2019-09-11T22:59:56.004+0800
2019-09-11 22:59:56.005 |-INFO  [pool-2-thread-6] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 57,时间 == >2019-09-11T22:59:56.005+0800
2019-09-11 22:59:58.005 |-INFO  [pool-2-thread-6] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 57,时间 == >2019-09-11T22:59:58.004+0800
2019-09-11 23:00:00.004 |-INFO  [pool-2-thread-6] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 57,时间 == >2019-09-11T23:00:00.004+0800
2019-09-11 23:00:01.010 |-INFO  [pool-2-thread-1] com.tutorial.controller.ScheduleTask [23] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 30 ,时间 == > 2019-09-11T23:00:01.010+0800
2019-09-11 23:00:02.005 |-INFO  [pool-2-thread-1] com.tutorial.controller.ScheduleTask [21] [ LOGID: ]-| 我是task—1,我的线程的 id == > 30,时间 == >2019-09-11T23:00:02.005+0800
2019-09-11 23:00:02.005 |-INFO  [pool-2-thread-4] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 55,时间 == >2019-09-11T23:00:02.005+0800
2019-09-11 23:00:04.005 |-INFO  [pool-2-thread-4] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 55,时间 == >2019-09-11T23:00:04.005+0800
2019-09-11 23:00:06.004 |-INFO  [pool-2-thread-4] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 55,时间 == >2019-09-11T23:00:06.004+0800
2019-09-11 23:00:07.011 |-INFO  [pool-2-thread-1] com.tutorial.controller.ScheduleTask [23] [ LOGID: ]-| task-1 ending ,我的线程的 id == > 30 ,时间 == > 2019-09-11T23:00:07.011+0800
2019-09-11 23:00:08.005 |-INFO  [pool-2-thread-5] com.tutorial.controller.ScheduleTask [21] [ LOGID: ]-| 我是task—1,我的线程的 id == > 56,时间 == >2019-09-11T23:00:08.005+0800
2019-09-11 23:00:08.006 |-INFO  [pool-2-thread-4] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 55,时间 == >2019-09-11T23:00:08.006+0800
2019-09-11 23:00:10.002 |-INFO  [pool-2-thread-4] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 55,时间 == >2019-09-11T23:00:10.002+0800
2019-09-11 23:00:12.001 |-INFO  [pool-2-thread-4] com.tutorial.controller.ScheduleTask [29] [ LOGID: ]-| 我是task-2,我的线程的 id == > 55,时间 == >2019-09-11T23:00:12.001+0800
```

### 配置参数
spring.task.scheduling.pool.size=10

## schedule即使出现异常了下一次还是会触发执行

