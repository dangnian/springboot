package com.dangnian.springboot.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.util.ErrorHandler;

@Configuration
public class SchedulingConfig implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler());
    }

    @Bean(name = "taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("dispatch-");
        scheduler.setAwaitTerminationSeconds(600);
        scheduler.setErrorHandler(new MyErrorHandle());
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }

    class MyErrorHandle implements ErrorHandler {

        @Override
        public void handleError(Throwable throwable) {

        }
    }
}
