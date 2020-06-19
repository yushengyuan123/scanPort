package com.web.socket;

import com.web.enumeration.constValue;
import com.web.ipInfo.ipInfo;

import java.net.*;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

public class socket {
    public static int scan(String address, int port) throws IOException {
        long startTime = System.currentTimeMillis();
        //type为0是关闭，type为1时tcp, type为-1时候为udp
        int type = -1;
        try {
            Socket client = new Socket(address, port);
            System.out.println("TCP成功连接");
            type = 1;
            client.close();
        } catch (Exception exception) {
            System.out.println("TCP失败连接");
        }
        //当type为-1时候说明连接TCP没有成功，马上尝试连接UDP
        if (type == -1) {
            try {
                DatagramSocket udpConnect = new DatagramSocket();
                udpConnect.setSoTimeout(constValue.WAIT_TIME);
                byte[] bs = "try to send data ".getBytes();
                DatagramPacket dp = new DatagramPacket(bs, bs.length,
                        InetAddress.getByName(address), port);
                udpConnect.send(dp);
                System.out.println("udp连接成功" + address);
            } catch (Exception ignored) {
                System.out.println("UDP连接失败");
                type = 0;
            }
        }
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
        return type;
    }

    /**
     * 测试主机是否可达
     */
    public static boolean isAddressReachable(String ip){
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (address.isReachable(constValue.WAIT_TIME)){
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
