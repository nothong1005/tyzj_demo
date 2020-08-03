package com.segmentfault.springbootlesson11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author 62667
 */

@Configuration
@EnableAsync
public class AsyncConfiguration {

    //创建线程池
    @Bean("taskExecutor")
    public Executor asyncExecutor() {

        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(5);
        threadPoolExecutor.setMaxPoolSize(5);
        threadPoolExecutor.setQueueCapacity(500);
        threadPoolExecutor.setKeepAliveSeconds(60);
        threadPoolExecutor.setThreadNamePrefix("OrderAsync-");
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }
}
