package com.lhh.demo.util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class SocketService {
    public static void main(String args[]) {
        try {
            System.out.println("Server start...");
            ServerSocket ss = new ServerSocket(8889);
            while (true) {
                Socket s = ss.accept();
                s.setSendBufferSize(384);
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                System.out.println("client connect...");
                FileInputStream fis = new FileInputStream("C:\\Users\\lai.huihui\\Desktop\\initChange - 1.txt");
                DataInputStream dis = new DataInputStream(fis);
                byte[] buffer = new byte[128];
                String sb;
                int n = 0;
                while ((n=dis.read(buffer)) >= 0) {
                    sb = bytesToHexString(buffer,n);
                    System.out.println("send to client " + sb.length() + " : " + sb);
                    System.out.println(System.currentTimeMillis());
                    dos.write(sb.getBytes());
                    System.out.println(System.currentTimeMillis());
                }
                fis.close();
                dis.close();
                dos.close();
                s.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String bytesToHexString(byte[] bArr,int off) {
        StringBuffer sb = new StringBuffer(bArr.length);
        String sTmp;
        if (off > bArr.length) {
            return "ERROR";
        }
        for (int i = 0; i < off; i++) {
            sTmp = Integer.toHexString(0xFF & bArr[i]);
            if (sTmp.length() < 2)
                sb.append(0);
            sb.append(sTmp.toUpperCase());
            sb.append(" ");
        }
        return sb.toString();
    }
}
