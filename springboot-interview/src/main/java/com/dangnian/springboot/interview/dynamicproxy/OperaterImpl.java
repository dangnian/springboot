package com.dangnian.springboot.interview.dynamicproxy;

public final class OperaterImpl implements IOperater{
    @Override
    public Integer query(Integer id) {
        System.out.println("进入查询方法");
        return id;
    }

    @Override
    public boolean insert(Integer id) {
        System.out.println("进入插入方法");
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(Integer id) {
        return false;
    }
}
