package com.dangnian.springboot.interview.socket;

import java.io.*;
import java.net.Socket;

public class SocketClientDemo {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8080);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
          /*  // 和服务端建立连接后若此处发生阻塞也会导致服务端阻塞以至于服务端不可用
            Thread.sleep(10000);*/
            // 换行符表示这一行数据发送完毕
            writer.write("我是客户端发送了一个消息\n");
            writer.flush();
            // 读取服务端返回的数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverLine = reader.readLine();
            System.out.println("收到服务端的数据:" + serverLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
