package com.dangnian.springboot.entity.test.vo;

import java.io.Serializable;

/**
 * 基础下拉框控件需要的返回值
 * @author chun.yin
 */
public class BaseSelectVO implements Serializable {

    private static final long serialVersionUID = 686879917760888482L;

    /**
     * 下拉名称
     */
    private String text;

    /**
     * 下拉值
     */
    private String id;

    /**
     * 下来索引顺序
     */
    private int index;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
