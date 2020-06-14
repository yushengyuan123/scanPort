package com.web.ipInfo;

public class ipInfo {
    private String ipAddress;
    private String port;

    public void setAddress(String value) {
        this.ipAddress = value;
    }

    public void setPort(String value) {
        this.port = value;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getPort() {
        return this.port;
    }
}
