package com.challenge.model;

public class OutgoingTask {
    private String name;
    private String command;

    public OutgoingTask() {
    }

    public OutgoingTask(String name, String command) {
        this.name = name;
        this.command = command;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }
}
