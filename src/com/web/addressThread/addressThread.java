package com.web.addressThread;

import com.web.socket.socket;

import java.io.IOException;

public class addressThread extends Thread {
    private Thread t;
    private final String address;
    private final int port;
    public addressThread(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void start () {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

    public void run() {
        socket ip = new socket();
        try {
            ip.scan(this.address, this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
