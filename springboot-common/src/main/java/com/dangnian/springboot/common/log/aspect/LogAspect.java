package com.dangnian.springboot.common.log.aspect;

import com.dangnian.springboot.common.log.annotation.Log;
import com.dangnian.springboot.common.util.GsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author chun.yin
 **/

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.dangnian.springboot.common.log.annotation.Log)")
    public void pointcut() { }

    @Around("pointcut()")
    public Object around (ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);
        String module = logAnnotation.module();
        String describe = logAnnotation.describe();
        String className = point.getTarget().getClass().getSimpleName();
        // 方法名
        String methodName = signature.getName();
        // 请求的方法参数值
        Object[] args = point.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        // 参数名称和值对应关系
        String params = "";
        if (args != null && paramNames != null) {
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + GsonUtils.toJson(args[i]);
            }
        }
        String operatorName = null;
        String tenantCode = null;
        String ip = null;
        try {
           // operatorName = SessionHolder.getSession().getAccountName();
           // tenantCode = SessionHolder.getSession().getTenantCode();
            ServletRequestAttributes requestAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            ip = getIpAddress(requestAttr.getRequest());
        } catch (Throwable e) {
            logger.error("日志拦截过程中获取request||session失败!模块[{}], 描述[{}], 类[{}], 方法[{}], 参数[{}]", module, describe, className, methodName, params, e);
        }
        Object result = null;
        // 方法执行前
        Long startTime = System.currentTimeMillis();
        try {
            logger.info("开始!模块[{}], 描述[{}], 类[{}], 方法[{}], 入参[{}], 租户[{}], 操作人[{}], ip[{}], ", module, describe, className, methodName, params, tenantCode, operatorName, ip);
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            logger.error("异常!模块[{}], 描述[{}], 类[{}], 方法[{}], 入参[{}], 租户[{}], 操作人[{}], ip[{}]", module, describe, className, methodName, params, tenantCode, operatorName, ip, e);
           // 由统一的异常拦截器处理，此处不做任何处理
            throw e;
        } finally {
            // 方法执行后
            logger.info("结束!模块[{}], 描述[{}], 类[{}], 方法[{}], 入参[{}], 租户[{}], 操作人[{}], ip[{}], 出参:[{}]", module, describe, className, methodName, params, tenantCode, operatorName, ip, GsonUtils.toJson(result));
            // 方法运行耗时
            logger.info("耗时!模块[{}], 描述[{}], 类[{}], 方法[{}], 入参[{}],  租户[{}], 操作人[{}], ip[{}], 耗时:[{}]毫秒", module, describe, className, methodName, params, tenantCode, operatorName, ip, System.currentTimeMillis()-startTime);
        }
        // 异步线程实例化线程，后期可优化
        return result;
    }

    public String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException var5) {
                    logger.error("未知主机异常", var5);
                }
            }
        }

        if (ipAddress != null && ipAddress.length() > 15 && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }


}
