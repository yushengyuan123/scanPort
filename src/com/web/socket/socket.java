package com.web.socket;

import java.net.*;
import java.io.*;
import java.io.IOException;

public class socket {
    public static int scan(String address, int port) throws IOException {
        //type为0是关闭，type为1时tcp, type为-1时候为udp
        int type = -1;
        try {
            Socket client = new Socket(address, port);
            type = 1;
            client.close();
        } catch (IOException ignored) {

        }
        //当type为-1时候说明连接TCP没有成功，马上尝试连接UDP
        if (type == -1) {
            try {
                DatagramSocket udpConnect = new DatagramSocket();
                byte[] bs = "try to send data ".getBytes();
                DatagramPacket dp = new DatagramPacket(bs, bs.length,
                        InetAddress.getByName(address), port);
                udpConnect.send(dp);
                type = 0;
            } catch (Exception ignored) {

            }
        }
        return type;
    }
}
