package com.dangnian.springboot.common.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Component
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private final static String DEFAULT_THREAD_NAME = "execute-";
    private final static int DEFAULT_MAX_POOL_SIZE = 100;
    private final static int DEFAULT_CORE_POOL_SIZE = 10;
    private final static int DEFAULT_QUEUE_CAPACITY = 100;
    private final static int DEFAULT_KEEP_ALIVE_SECOND = 60;

    /**
     * 默认的线程池获取方法
     * @return
     */
    @Bean("defaultAsyncExecutor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(DEFAULT_THREAD_NAME);
        executor.setMaxPoolSize(DEFAULT_MAX_POOL_SIZE);
        executor.setCorePoolSize(DEFAULT_CORE_POOL_SIZE);
        executor.setQueueCapacity(DEFAULT_QUEUE_CAPACITY);
        executor.setAllowCoreThreadTimeOut(false);
        executor.setKeepAliveSeconds(DEFAULT_KEEP_ALIVE_SECOND);
        executor.setTaskDecorator(new DefaultContextDecorator());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }


    class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            //相关操作
        }
    }


    class DefaultContextDecorator implements TaskDecorator {
        RequestAttributes context = RequestContextHolder.currentRequestAttributes();
        @Override
        public Runnable decorate(Runnable runnable) {
            return () -> {
                try {
                    RequestContextHolder.setRequestAttributes(context);
                    runnable.run();
                } finally {
                    RequestContextHolder.resetRequestAttributes();
                }
            };
        }
    }

}
