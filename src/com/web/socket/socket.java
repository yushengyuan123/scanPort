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
        //type为0是关闭，type为1时tcp, type为-1时候为udp
        int type = -1;
        try {
            Socket client = new Socket(address, port);
            type = 1;
            client.close();
        } catch (Exception ignored) {

        }
        //当type为-1时候说明连接TCP没有成功，马上尝试连接UDP
        if (type == -1) {
            byte[] data = address.getBytes();
            try (DatagramSocket socket = new DatagramSocket()){
                socket.setSoTimeout(1000);
                socket.setTrafficClass(0x04 | 0x10);
                socket.connect(new InetSocketAddress(address, port));
                socket.send(new DatagramPacket(data, data.length));
                while (true) {
                    byte[] receive = new byte[4096];
                    DatagramPacket dp = new DatagramPacket(receive, 4096);
                    socket.receive(dp);
                    if (dp.getData() != null) {
                        System.out.println("udp建立成功");
                        type = 0;
                    }
                }
            } catch (Exception ignored) {

            }
        }
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
