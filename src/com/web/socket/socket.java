package com.web.socket;

import java.net.*;
import java.io.*;
import java.io.IOException;

public class socket {
    public void scan(String address, int port) throws IOException {
        if (address != null) {
            try {
                System.out.println("连接到主机：" + address + " ，端口号：" + port);
                Socket client = new Socket(address, port);
                System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                out.writeUTF("Hello from " + client.getLocalSocketAddress());
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println("服务器响应： " + in.readUTF());
                client.close();
            } catch (IOException e) {
                System.out.println("该端口未被使用");
            }

        } else {
            System.out.println("地址为空");
        }

    }
}
