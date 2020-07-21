package com.dangnian.springboot.interview.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MutiSocketService implements Runnable{

    private Socket socket;

    public MutiSocketService(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientStr = reader.readLine();
            System.out.println("接收到客户端的连接" + clientStr);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("我收到了客户端的信息");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
