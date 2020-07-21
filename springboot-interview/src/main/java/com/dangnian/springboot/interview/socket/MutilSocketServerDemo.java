package com.dangnian.springboot.interview.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutilSocketServerDemo {

    static ExecutorService executorService = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            // 阻塞式监听客户端的连接
            Socket socket = serverSocket.accept();
            System.out.println(socket.getPort());
            executorService.execute(new MutiSocketService(socket));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
