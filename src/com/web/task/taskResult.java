package com.web.task;

public class taskResult {
    private int taskId;

    private String startAddress;

    private String endAddress;

    private int startPort;

    private int endPort;

    private int hasFinish;

    private String sort;

    public taskResult(int taskId, String startAddress, String endAddress, int startPort, int endPort, int hasFinish, String sort) {
        this.taskId = taskId;

        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.startPort = startPort;
        this.endPort = endPort;
        this.hasFinish = hasFinish;
        this.sort = sort;
    }
}
