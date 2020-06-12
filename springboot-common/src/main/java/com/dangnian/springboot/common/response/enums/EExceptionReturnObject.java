package com.dangnian.springboot.common.response.enums;

import com.dangnian.springboot.entity.test.vo.DataGridVO;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Stream;

/**
 * @author chun.yin
 */

public enum EExceptionReturnObject {

    MODEL_AND_VIEW(ModelAndView.class, new ModelAndView("/scmbase/error")),
    DATA_GRID_VO(DataGridVO.class, new DataGridVO<>());

    /**
     * 返回对象类型
     */
    private Class returnType;

    /**
     * 默认的异常返回对象
     */
    private Object exceptionReturnObject;

    EExceptionReturnObject(Class returnType, Object exceptionReturnObject) {
        this.returnType = returnType;
        this.exceptionReturnObject = exceptionReturnObject;
    }

    public Class returnType() {
        return this.returnType;
    }

    public Object exceptionReturnObject() {
        return this.exceptionReturnObject;
    }

    public static boolean containReturnType(Class clazz) {
        if (null == clazz) {
            return false;
        }
        return Stream.of(EExceptionReturnObject.values())
                .anyMatch(e -> clazz.equals(e.returnType));
    }

    public static Object getExceptionReturnObjectByReturnType (Class clazz) {
        if (null == clazz) {
            return null;
        }
        return Stream.of(EExceptionReturnObject.values())
                .filter(e -> clazz.equals(e.returnType))
                .findAny()
                .map(EExceptionReturnObject::exceptionReturnObject)
                .orElse(null);
    }

}
