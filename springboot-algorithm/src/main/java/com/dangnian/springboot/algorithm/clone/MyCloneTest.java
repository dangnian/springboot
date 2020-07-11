package com.dangnian.springboot.algorithm.clone;

import java.io.*;

/**
 * 使用序列化来实现克隆可以更加的简单避免
 * 多层引用类型嵌套实现深克隆的复杂性
 */
public class MyCloneTest implements Serializable {

    public MyCloneTest myClone() {
        MyCloneTest myCloneTest = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            myCloneTest = (MyCloneTest) ois.readObject();
        } catch (Exception e) {

        }
        return myCloneTest;
    }
}
