package com.web.ipInfo;

public class ipInfo {
    private String ipAddress;

    private int port;

    private final String service;

    private final String agreement;

    private final boolean status;

    public ipInfo(String address, int port, boolean status, String service, String agreement) {
        this.ipAddress = address;
        this.port = port;
        this.status = status;
        this.service = service;
        this.agreement = agreement;
    }

    public void setAddress(String value) {
        this.ipAddress = value;
    }

    public void setPort(int value) {
        this.port = value;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public int getPort() {
        return this.port;
    }

    public String getService() {
        return this.service;
    }

    public boolean isStatus() {
        return this.status;
    }

    public String getAgreement() {
        return this.agreement;
    }
}
