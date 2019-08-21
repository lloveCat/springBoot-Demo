package com.lhh.demo.util;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {
    public static void main(String args[]) throws Exception{
        Socket s = null;
        DataInputStream br = null;
        try {
            s = new Socket("127.0.0.1", 8889);
            s.setReceiveBufferSize(384);
            System.out.println("connect to Server success");
            byte []buffer = new byte[384];
            br = new DataInputStream(s.getInputStream());
            int n = 0;
            while ((n = br.read(buffer)) != -1) {
                System.out.println(System.currentTimeMillis());
                String a = new String(buffer,0,n);
                System.out.println("receiver from server" + a.length() + " : " + a);
                if (s.getInputStream().available() == 0) {
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("连接服务端失败...");
            if (s != null) {
                s.close();
            }
            if (br != null) {
                br.close();
            }
        }
    }
}
