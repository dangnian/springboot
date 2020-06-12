package com.dangnian.springboot.common.util;

import com.dangnian.springboot.entity.test.vo.BaseSelectVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jun.liu on 2017-12-22.
 * 此方法仅限枚举接口调用，外部系统请调用枚举服务接口，请勿调用该方法
 */
public class EnumUtils {
	
	private EnumUtils(){}


    //所有枚举定义请统一定义编号名称为code,显示字段名：desc，如果不统一就可能造成枚举服务失效后，加载本地枚举字段失败
    /**
     * 枚举code字段名称
     */
    public static final String CODE_PROP_NAME = "code";

    /**
     * 枚举描述字段名称
     */
    public static final String DESC_PROP_NAME = "desc";

    /**
     * 枚举转map，key=编号，value=描述
     * @param enumData
     * @return
     */
    public static Map<String, String> enumProp2NameMap(Class<? extends Enum> enumData) {
        if (!enumData.isEnum()) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        try {
            Enum[] enums = enumData.getEnumConstants();

            for (Enum en : enums) {
                String key = (String) FieldUtils.readDeclaredField(en, CODE_PROP_NAME, true);
                String value = (String) FieldUtils.readDeclaredField(en, DESC_PROP_NAME, true);
                map.put(key, value);
            }
            return map;
        } catch (Exception e) {

        }
        return map;
    }


    /**
     * 枚举转List
     * @param enumData
     * @param propName 对应枚举名称属性名字,若不填默认为name字段名称
     * @param propValue 对应枚值属性名字,若不填默认为value字段名称
     * @return
     */
    public static List<BaseSelectVO> enumProp2List(Class<? extends Enum> enumData, String propName, String propValue) {
        if(!enumData.isEnum()){
            return null;
        }
        List voList = new ArrayList<BaseSelectVO>();
        String enumName = StringUtils.isEmpty(propName) ? DESC_PROP_NAME:propName;
        String enumvalue = StringUtils.isEmpty(propValue) ? CODE_PROP_NAME : propValue;
        try {
            Enum[] enums = enumData.getEnumConstants();
            for(Enum en:enums) {
                FieldUtils.readDeclaredField(en,enumName, true);
                BaseSelectVO vo = new BaseSelectVO();
                vo.setText(String.valueOf(FieldUtils.readDeclaredField(en,enumName,true)));
                vo.setId(String.valueOf(FieldUtils.readDeclaredField(en, enumvalue,true)));
                voList.add(vo);
            }
        } catch  (Exception e) {

        }
        return voList;
    }


}
