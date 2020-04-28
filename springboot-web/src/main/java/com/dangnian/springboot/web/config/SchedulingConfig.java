package com.dangnian.springboot.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;


/**
 * @author chun.yin
 */
@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {

    private final static Logger LOGGER = LoggerFactory.getLogger(SchedulingConfig.class);

    @Value("${scheduler.poolSize:10}")
    private int poolSize;

    @Value("${scheduler.threadNamePrefix:schedulerDefault-}")
    private String threadNamePrefix;

    @Value("${scheduler.awaitTerminationSeconds:600}")
    private int awaitTerminationSeconds;

    @Value("${scheduler.waitForTasksToCompleteOnShutdown:true}")
    private boolean waitForTasksToCompleteOnShutdown;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler());
    }

    @Bean(name = "defaultScheduler")
    public TaskScheduler taskScheduler() {
        return createScheduler(threadNamePrefix);
    }

    private TaskScheduler createScheduler(String threadNamePrefix) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(poolSize);
        scheduler.setThreadNamePrefix(threadNamePrefix);
        scheduler.setAwaitTerminationSeconds(awaitTerminationSeconds);
        scheduler.setErrorHandler((Throwable t) -> LOGGER.error("线程池执行任务发生未知异常!异常信息:{}", t.getMessage(), t));
        scheduler.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        scheduler.initialize();
        return scheduler;
    }

}
