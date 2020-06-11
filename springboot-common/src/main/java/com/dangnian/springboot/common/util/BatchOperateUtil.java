package com.dangnian.springboot.common.util;

import com.zhaogang.saas.voucher.common.base.enums.EResultCode;
import com.zhaogang.saas.voucher.common.base.exception.CommonRuntimeException;
import com.zhaogang.saas.voucher.common.base.model.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chun.yin
 */
public class BatchOperateUtil {

    private static final Logger logger = LoggerFactory.getLogger(BatchOperateUtil.class);

    public static <T, R, U> Result execute(List<T> sourceList,
                                           Function<? super T, ? extends R> processHandler,
                                           Function<? super R, Boolean> resultFilter,
                                           Function<? super R, String> resultHandler,
                                           Function<? super T, ? extends U> uniqueIdentifier) {
        return baseExecute(sourceList, t -> {
            try {
                R r = processHandler.apply(t);
                if (null != r && !resultFilter.apply(r)) {
                    return buildFailureAdvice(String.valueOf(uniqueIdentifier.apply(t)), resultHandler.apply(r));
                } else {
                    return buildSuccessAdvice();
                }
            } catch(CommonRuntimeException e) {
                logger.warn("批量请求处理时单笔执行已知异常!数据:[{}]", GsonUtils.toJson(t), e);
                return buildFailureAdvice(String.valueOf(uniqueIdentifier.apply(t)), e.getMessage());
            } catch (Exception e) {
                logger.warn("批量请求处理时单笔执行未知异常!数据:[{}]", GsonUtils.toJson(t), e);
                return buildFailureAdvice(String.valueOf(uniqueIdentifier.apply(t)), "未知异常，请联系管理员");
            }
        });
    }

    public static <T, U> Result execute(List<T> sourceList, Consumer<? super T> processHandler, Function<? super T, ? extends U> uniqueIdentifier) {
        return baseExecute(sourceList, t -> {
            try {
                processHandler.accept(t);
            } catch(CommonRuntimeException e) {
                logger.warn("批量请求处理时单笔执行已知异常!数据:[{}]", GsonUtils.toJson(t), e);
                return buildFailureAdvice(String.valueOf(uniqueIdentifier.apply(t)), e.getMessage());
            } catch (Exception e) {
                logger.warn("批量请求处理时单笔执行未知异常!数据:[{}]", GsonUtils.toJson(t), e);
                return buildFailureAdvice(String.valueOf(uniqueIdentifier.apply(t)), "未知异常，请联系管理员");
            }
            return buildSuccessAdvice();
        });
    }

    private static <T> Result baseExecute(List<T> sourceList, Function<? super T, String> abstractHandler) {
        if (CollectionBaseUtils.isEmpty(sourceList)){
            return buildErrorResult(0, 0, "批量请求处理时参数为空，请联系管理员");
        }
        // 返回循环处理时失败的情况
        List<String> failureAdviceList = sourceList.stream()
                .map(abstractHandler)
                .filter(BatchOperateUtil::failureAdviceFilter)
                .collect(Collectors.toList());
        if (CollectionBaseUtils.isNotEmpty(failureAdviceList)) {
            return buildErrorResult(sourceList.size(), failureAdviceList.size(), failureAdviceList);
        } else {
            return buildSuccessResult(sourceList.size());
        }
    }

    private static String buildFailureAdvice(String uniqueIdentify, String adviceMessage) {
        return String.format("单据编号[%s]失败原因：%s", uniqueIdentify, adviceMessage);
    }

    private static String buildSuccessAdvice() {
        return EResultCode.SUCCESS.code();
    }

    private static boolean failureAdviceFilter(String advice) {
        return !EResultCode.SUCCESS.code().equals(advice);
    }

    private static Result buildErrorResult(int sourceCount, int failureCount, Object failureAdvice) {
        return ResultUtils.failure(EResultCode.FAILURE.code(), String.format("您选择了%s张单据，成功%s张，失败%s张", sourceCount, sourceCount-failureCount, failureCount), failureAdvice);
    }

    private static Result buildSuccessResult(int successCount) {
        return ResultUtils.success(String.format("成功处理%s张单据", successCount));
    }

}
