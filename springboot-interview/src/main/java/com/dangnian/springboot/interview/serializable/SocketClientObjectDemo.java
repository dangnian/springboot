package com.dangnian.springboot.interview.serializable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientObjectDemo {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            User user = new User();
            user.setName("yc");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
