package com.dangnian.springboot.interview.dynamicproxy;

public interface IOperater {

    Integer query(Integer id);

    boolean insert(Integer id);

    boolean delete(Integer id);

    boolean update(Integer id);
}
