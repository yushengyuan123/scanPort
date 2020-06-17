package com.web.service;

import com.web.ipInfo.ipInfo;
import com.web.socket.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class dealSingle {
    public static List<ipInfo> querySingleSocket(String address, int port) throws IOException {
        List<ipInfo> list = new ArrayList<>();
        int scanResult = socket.scan(address, port);

        if (scanResult == 1) {
            list.add(new ipInfo(address, port, true,"未知", "TCP"));
        } else if (scanResult == -1) {
            list.add(new ipInfo(address, port, true,"未知", "UDP"));
        }

        return list;
    }
}
