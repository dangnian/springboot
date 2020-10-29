package com.dangnian.springboot.interview.classLoader;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{

    private String classPath;

    public MyClassLoader(ClassLoader parent, String classPath) {
        super(parent);
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(classPath + "/" + name + ".class");
            out = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[2048];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer,0,len);
            }
            byte[] data = out.toByteArray();
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        throw new ClassNotFoundException();
    }

    public static void main(String[] args) {
        // 通过类的全限定名和指定类加载器获取指定的Class.forName-> Class<?>
        // 通过反射class.newInstance将class加载到内存中
    }
}
