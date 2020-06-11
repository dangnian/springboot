
package com.dangnian.springboot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author chun.yin
 **/
public class KeepAliveUtil {

    private static Logger logger  = LoggerFactory.getLogger(KeepAliveUtil.class);

    public static <R> R executor(HttpServletResponse response, Supplier<R> supplier) {
        KeepAliveGuard keepAliveGuard = new KeepAliveGuard(response);
        try {
            keepAliveGuard.register();
            return supplier.get();
        } catch (Exception e) {
            throw e;
        } finally {
            keepAliveGuard.unRegister();
        }
    }

    public static <T> void executor(T t, HttpServletResponse response, Consumer<T> consumer) {
        KeepAliveGuard keepAliveGuard = new KeepAliveGuard(response);
        try {
            keepAliveGuard.register();
            consumer.accept(t);
        } catch (Exception e) {
            throw e;
        } finally {
            keepAliveGuard.unRegister();
        }
    }

    public static <T, R> R executor(T t, HttpServletResponse response, Function<T, R> function) {
        KeepAliveGuard keepAliveGuard = new KeepAliveGuard(response);
        try {
            keepAliveGuard.register();
            return function.apply(t);
        } catch (Exception e) {
            throw e;
        } finally {
            keepAliveGuard.unRegister();
        }
    }

    public static <T, U, R> R executor(T t, U u, HttpServletResponse response, BiFunction<T, U, R> biFunction) {
            KeepAliveGuard keepAliveGuard = new KeepAliveGuard(response);
            try {
                keepAliveGuard.register();
                return biFunction.apply(t, u);
            } catch (Exception e) {
                throw e;
            } finally {
            keepAliveGuard.unRegister();
        }
    }

    public static class KeepAliveGuard {

        //上传的守卫线程池
        private ExecutorService uploadGuardService = Executors.newSingleThreadExecutor();
        //上传心跳线程
        private KeepAliveRunnable keepAliveRunnable;
        //存活标记
        private final AtomicBoolean flag = new AtomicBoolean(true);
        //等待管控器
        private final CountDownLatch countLatch = new CountDownLatch(1);

        public KeepAliveGuard(HttpServletResponse response){
            keepAliveRunnable = new KeepAliveRunnable(response);
        }

        public void register(){
            uploadGuardService.execute(keepAliveRunnable);
        }

        public void unRegister(){
            if(flag.compareAndSet(true,false)){
                //等待器结束
                countLatch.countDown();
                //线程关闭
                if(uploadGuardService != null && !uploadGuardService.isShutdown()){
                    uploadGuardService.shutdown();
                }
            }
        }

        private boolean isActive(){
            return flag.get();
        }

        private void waitMoment(long timeout, TimeUnit unit){
            try {
                countLatch.await(timeout, unit);
            } catch (InterruptedException e) {
                logger.error("等待异常,原因:", e);
            }
        }

        public class KeepAliveRunnable implements Runnable{
            HttpServletResponse response;
            public KeepAliveRunnable(HttpServletResponse response){
                this.response = response;
            }
            @Override
            public void run() {
                while (isActive()){
                    sendHeartbeat();
                    waitNext();
                }
            }
            private void sendHeartbeat(){
                try {
                    response.getOutputStream().write(" ".getBytes());
                    response.getOutputStream().flush();
                } catch (IOException e) {
                    logger.error("心跳保持会写异常,原因:", e);
                }
            }
            private void waitNext(){
                waitMoment(10, TimeUnit.MILLISECONDS);
            }
        }
    }


}

