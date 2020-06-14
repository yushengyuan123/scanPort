package com.web.business;

import com.web.addressThread.addressThread;

public class business {
    private final String address;
    public business(String address) {
        this.address = address;
    }
    public void startScan() {
        for (int i = 8079; i < 9000; i++) {
            addressThread ipThread = new addressThread(this.address, i);
            ipThread.start();
        }
    }
}
