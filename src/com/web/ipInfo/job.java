package com.web.ipInfo;

public class job {

    private String ip;

    private String port;

    private int portMin;

    private int portMax;

    //任务性质
    private String character = null;
//
//    private String multiPort;

    public job(String ip, String port, String portMin, String portMax, String character) {
        this.ip = ip;
        this.port = port;
        this.portMin = Integer.parseInt(portMin);
        this.portMax = Integer.parseInt(portMax);
        this.character = character;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public int getPortMin() {
        return portMin;
    }

    public int getPortMax() {
        return portMax;
    }

    public String getCharacter() {
        return character;
    }
}
