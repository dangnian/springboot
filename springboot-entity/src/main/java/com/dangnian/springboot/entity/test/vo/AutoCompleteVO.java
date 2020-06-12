package com.dangnian.springboot.entity.test.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * jquery.autocomplete.js需要的返回值
 */
public class AutoCompleteVO {
    private String query;//查询条件
    private List<Suggestion> suggestions;//返回值

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void init(String query) {
        this.query = query;
        this.suggestions = new ArrayList<>();
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    /**
     * 添加一个返回值
     *
     * @param data
     * @param value
     */
    public void addValue(String data, String value) {
        suggestions.add(new Suggestion(data, value));
    }
    
    public void addValue(String data, String value,Object obj) {
        suggestions.add(new Suggestion(data, value,obj));
    }
}

class Suggestion {
    private String data;
    private String value;
    private Object object;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    

    public Suggestion(String data, String value) {
        this.data = data;
        this.value = value;
    }
   

    public Suggestion(String data, String value, Object obj) {
        this.data = data;
        this.value = value;
        this.object = obj;
    }
    
    public Suggestion() {
    }

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
    
    
}
