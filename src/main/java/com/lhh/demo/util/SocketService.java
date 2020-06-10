package com.lhh.demo.util;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class SocketService {
    public static void main(String args[]) {
        try {
//            System.out.println("Server start...");
//            ServerSocket ss = new ServerSocket(8889);
//            while (true) {
//                Socket s = ss.accept();
//                s.setSendBufferSize(384);
//                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
//                System.out.println("client connect...");
//                FileInputStream fis = new FileInputStream("C:\\Users\\lai.huihui\\Desktop\\initChange - 1.txt");
//                DataInputStream dis = new DataInputStream(fis);
//                byte[] buffer = new byte[128];
//                String sb;
//                int n = 0;
//                while ((n=dis.read(buffer)) >= 0) {
//                    sb = bytesToHexString(buffer,n);
//                    System.out.println("send to client " + sb.length() + " : " + sb);
//                    System.out.println(System.currentTimeMillis());
//                    dos.write(sb.getBytes());
//                    System.out.println(System.currentTimeMillis());
//                }
//                fis.close();
//                dis.close();
//                dos.close();
//                s.close();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            Selector selector = Selector.open();
            SocketAddress sa = new InetSocketAddress(8889);
            ssc.bind(sa);
            SocketChannel sc = SocketChannel.open(sa);
            System.out.println("server is open !!!!");
            while (true) {
                ssc.configureBlocking(false);
                ssc.register(selector, SelectionKey.OP_ACCEPT);
                selector.select(10000);
                System.out.println("ready to select !!!!");

                Set<SelectionKey> readKeys = selector.selectedKeys();
                Iterator<SelectionKey> is = readKeys.iterator();
                while (is.hasNext()) {
                    SelectionKey sk = is.next();
                    if (sk.isAcceptable()) {
                        handleAccept(sk);
                    } else if (sk.isReadable()) {
                        handleReadable(sk);
                    } else if (sk.isWritable()) {
                        handleWritable(sk);
                    }
                    is.remove();
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleReadable(SelectionKey key) {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer bb = (ByteBuffer) key.attachment();
        System.out.println("strat to read");
        try {
            long length = sc.read(bb);
            while (length > 0) {
                bb.flip();
                while (bb.hasRemaining()) {
                    System.out.println((char)bb.get());
                }
                System.out.println();
                bb.clear();
                length = sc.read(bb);
            }
            if (length == -1) {
                sc.close();
            }
        } catch (Exception e) {}
        System.out.println("end to read");
    }

    public static void handleWritable(SelectionKey key) {
        ByteBuffer bb = (ByteBuffer) key.attachment();
        bb.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while (bb.hasRemaining()) {
            try {
                sc.write(bb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bb.compact();
    }

    public static void handleAccept(SelectionKey key) {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        try{
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1000));
        } catch (Exception e) {}
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
