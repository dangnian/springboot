package com.dangnian.springboot.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author chun.yin
 */

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private final static Logger LOGGER = LoggerFactory.getLogger(AsyncConfig.class);

    @Value("${async.corePoolSize:5}")
    private int corePoolSize;

    @Value("${async.maxPoolSize:10}")
    private int maxPoolSize;

    @Value("${async.keepAliveSeconds:60}")
    private int keepAliveSeconds;

    @Value("${async.queueCapacity:1024}")
    private int queueCapacity;

    @Value("${async.allowCoreThreadTimeout:false}")
    private boolean allowCoreThreadTimeout;

    @Value("${async.threadNamePrefix:defaultExecutor-}")
    private String threadNamePrefix;

    @Override
    @Bean("defaultExecutor")
    public Executor getAsyncExecutor() {
        return createExecutor(threadNamePrefix);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> LOGGER.error("线程池执行任务发生未知异常!方法:" + method.getName()
                + ",参数:" + params, ex);
    }

    private Executor createExecutor(String threadNamePrefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeout);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean(value = "testExecutor")
    public Executor testExecutor() {
        return createExecutor("testExecutor");
    }

}
