//package com.conductor.shortenurl.config;
//
//import com.baidu.fsg.uid.UidGenerator;
//import com.baidu.fsg.uid.buffer.RejectedPutBufferHandler;
//import com.baidu.fsg.uid.buffer.RejectedTakeBufferHandler;
//import com.baidu.fsg.uid.impl.CachedUidGenerator;
//import com.baidu.fsg.uid.worker.WorkerIdAssigner;
//import com.conductor.shortenurl.worker.DisposableWorkerIdAssigner;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConditionalOnClass(UidGenerator.class)
//@EnableConfigurationProperties(BaiduShortUrlProperties.class)
//public class BaiduShortUrlGeneratorConfiguration {
//    private static final Logger log = LoggerFactory.getLogger(BaiduShortUrlGeneratorConfiguration.class);
//    private final BaiduShortUrlProperties uidGeneratorProperties;
//
//    @Autowired
//    public BaiduShortUrlGeneratorConfiguration(BaiduShortUrlProperties uidGeneratorProperties) {
//        this.uidGeneratorProperties = uidGeneratorProperties;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(WorkerIdAssigner.class)
//    public WorkerIdAssigner workerIdAssigner() {
//        return new DisposableWorkerIdAssigner();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(RejectedPutBufferHandler.class)
//    public RejectedPutBufferHandler rejectedPutBufferHandler() {
//        return (ringBuffer, uid) -> log.warn("Rejected putting buffer for uid:{}. {}", uid, ringBuffer);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(RejectedTakeBufferHandler.class)
//    public RejectedTakeBufferHandler rejectedTakeBufferHandler() {
//        return (ringBuffer) -> {
//            log.warn("Rejected take buffer. {}", ringBuffer);
//            throw new RuntimeException("Rejected take buffer. " + ringBuffer);
//        };
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(UidGenerator.class)
//    public UidGenerator uidGenerator(WorkerIdAssigner workerIdAssigner,
//        RejectedPutBufferHandler rejectedPutBufferHandler,
//        RejectedTakeBufferHandler rejectedTakeBufferHandler) {
//        // 使用缓冲生成器
//        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
//        // 设置初始化workerId生成方式
//        cachedUidGenerator.setWorkerIdAssigner(workerIdAssigner);
//        // 配置参数
//        cachedUidGenerator.setTimeBits(uidGeneratorProperties.getTimeBits());
//        cachedUidGenerator.setWorkerBits(uidGeneratorProperties.getWorkerBits());
//        cachedUidGenerator.setSeqBits(uidGeneratorProperties.getSeqBits());
//        cachedUidGenerator.setEpochStr(uidGeneratorProperties.getEpochStr());
//        cachedUidGenerator.setBoostPower(uidGeneratorProperties.getBoostPower());
//        if (uidGeneratorProperties.getScheduleInterval() != null) {
//            cachedUidGenerator.setScheduleInterval(uidGeneratorProperties.getScheduleInterval());
//        }
//        // 拒绝策略: 当环已满, 无法继续填充时
//        cachedUidGenerator.setRejectedPutBufferHandler(rejectedPutBufferHandler);
//        // 拒绝策略: 当环已空, 无法继续获取时
//        cachedUidGenerator.setRejectedTakeBufferHandler(rejectedTakeBufferHandler);
//
//        return cachedUidGenerator;
//    }
//}
