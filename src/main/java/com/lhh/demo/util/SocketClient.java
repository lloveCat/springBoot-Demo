package com.lhh.demo.util;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class SocketClient {
    public static void main(String args[]) throws Exception{
//        Socket s = null;
////        DataInputStream br = null;
////        try {
////            s = new Socket("127.0.0.1", 8889);
////            s.setReceiveBufferSize(384);
////            System.out.println("connect to Server success");
////            byte []buffer = new byte[384];
////            br = new DataInputStream(s.getInputStream());
////            int n = 0;
////            while ((n = br.read(buffer)) != -1) {
////                System.out.println(System.currentTimeMillis());
////                String a = new String(buffer,0,n);
////                System.out.println("receiver from server" + a.length() + " : " + a);
////                if (s.getInputStream().available() == 0) {
////                    return;
////                }
////            }
////        } catch (Exception e) {
////            System.out.println("连接服务端失败...");
////            if (s != null) {
////                s.close();
////            }
////            if (br != null) {
////                br.close();
////            }
////        }
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        ByteBuffer bb = ByteBuffer.allocate(1024);
        SocketAddress sa = new InetSocketAddress("localhost", 8889);
        sc.connect(sa);
        if (sc.finishConnect()) {
            int i = 0;
            while (true) {
//                TimeUnit.SECONDS.sleep(1);
                String info = "I am client";
                bb.clear();
                bb.put(info.getBytes());
                bb.flip();
                while (bb.hasRemaining()) {
                    System.out.println(bb.toString());
                    sc.write(bb);
                }
            }
        }
        if (sc != null) {
            sc.close();
        }
    }
}
