package com.dangnian.springboot.interview.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            // 阻塞式监听客户端的连接
            Socket socket = serverSocket.accept();
            System.out.println(socket.getPort());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientStr = reader.readLine();
            System.out.println("接收到客户端的连接:" + clientStr);
           /* // 若此处发生阻塞会导致服务端阻塞以至于不可用
            Thread.sleep(10000);*/
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 换行符标识这一行数据已发送完毕
            writer.write("我收到了客户端的信息\n");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
