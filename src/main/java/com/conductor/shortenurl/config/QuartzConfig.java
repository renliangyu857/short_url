package com.conductor.shortenurl.config;
import com.conductor.shortenurl.quartz.DeleteExpiredUrlMappingsJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        //指定任务描述具体的实现类
        return JobBuilder.newJob(DeleteExpiredUrlMappingsJob.class)
                // 指定任务的名称
                .withIdentity("DeleteExpiredUrlMappingsJob")
                // 任务描述
                .withDescription("任务描述：用于删除数据库中过期数据")
                .build();
    }

    @Bean
    public Trigger trigger() {
        //创建触发器
        return TriggerBuilder.newTrigger()
                // 绑定工作任务
                .forJob(jobDetail())
                // 每隔 1个小时 执行一次 job
                .withSchedule(SimpleScheduleBuilder.repeatHourlyForever(1))
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setAutoStartup(true); // 调度器会随Spring容器一起启动
        return schedulerFactory;
    }
}
