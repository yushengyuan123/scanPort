package com.web.ipInfo;

public class job {
    //起始ip地址
    private String ip;

    //终点ip地址
    private String endIp;

    private String port;

    private int portMin;

    private int portMax;

    //任务性质
    private String character;
//
//    private String multiPort;

    public job(String ip, String endAddress, String port, String portMin, String portMax, String character) {
        this.ip = ip;
        this.endIp = endAddress;
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

    public void setCharacter(String sort) { this.character = sort; }

    public String getEndIp() {
        return endIp;
    }
}
