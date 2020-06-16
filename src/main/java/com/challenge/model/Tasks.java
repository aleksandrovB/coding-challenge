package com.challenge.model;

public class Tasks {
    private IncomingTask[] tasks;

    public Tasks() {
    }

    public Tasks(IncomingTask[] tasks) {
        this.tasks = tasks;
    }

    public IncomingTask[] getTasks() {
        return tasks;
    }

    public void setTasks(IncomingTask[] tasks) {
        this.tasks = tasks;
    }
}
