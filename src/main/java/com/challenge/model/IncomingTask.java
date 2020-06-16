package com.challenge.model;


import org.springframework.stereotype.Component;

@Component
public class IncomingTask {
    private String name;
    private String command;
    private String[] requires;

    public IncomingTask() {
    }

    public IncomingTask(String name, String command) {
        this.name = name;
        this.command = command;
    }

    public IncomingTask(String name, String command, String... tasks) {
        this.name = name;
        this.command = command;
        this.requires = tasks;
    }

    public String[] getRequires() {
        if (requires != null) {
            return requires;
        }
        return new String[0];
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
    }
}
