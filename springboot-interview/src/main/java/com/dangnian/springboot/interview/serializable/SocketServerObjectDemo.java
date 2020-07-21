package com.dangnian.springboot.interview.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerObjectDemo {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            // java默认的序列化方式
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            User user = (User) objectInputStream.readObject();
            System.out.println(user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
