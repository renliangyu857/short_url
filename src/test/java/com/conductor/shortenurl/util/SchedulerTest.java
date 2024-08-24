package com.conductor.shortenurl.util;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class SchedulerTest {
    @Test
    public void testScheculer() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(4);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("delete from url_mapping where expire_time < now()");
        }, 11, 24, TimeUnit.HOURS);
        //小顶堆
        // 在内存里面
    }
}
