package com.conductor.shortenurl.quartz;

import com.conductor.shortenurl.repository.UrlRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class DeleteExpiredUrlMappingsJob implements Job {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始执行定时删除数据库过期数据任务...");
        urlRepository.deleteExpiredUrlMappings();
        System.out.println("删除数据库过期数据成功...");
    }
}
