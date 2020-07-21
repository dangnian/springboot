package com.dangnian.springboot.interview.socket;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOSocketServerDemo {

    static Selector selector;

    public static void main(String[] args) {
        // NIO Channel(多连接 多通道)/Buffer(缓冲区读取数据)/Selector(多路复用器) select/poll-》epoll(事件机制)
        try {
            // 打开多路复用器 通道必须设置为非阻塞
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 必设置为非阻塞,若不设置则默认为阻塞的
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            // 把连接时间注册到多路复用器上
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                //阻塞在多路复用器上
                selector.select();
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    // 连接事件
                    if (key.isAcceptable()) {
                        handleAccept(key);
                        //读事件
                    } else if(key.isReadable()) {
                        handleRead(key);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleAccept(SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.write(ByteBuffer.wrap("我是NIO服务端".getBytes()));
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleRead(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
