package com.challenge.service;

import com.challenge.exceptions.UnsupportedJsonFormat;
import com.challenge.model.IncomingTask;
import com.challenge.model.OutgoingTask;

import java.util.*;

public class TaskSorterService {
    private Set<String> done = new HashSet<>();
    private Map<String, IncomingTask> allTasks = new HashMap<>();
    private Queue<IncomingTask> sortedTasks = new LinkedList<>();

    private Queue<OutgoingTask> outgoingTasks = new LinkedList<>();

    public Queue<IncomingTask> getSortedTasks() {
        return sortedTasks;
    }

    public Queue<OutgoingTask> getOutgoingTasks() {
        return outgoingTasks;
    }

    /**
     * Sorts tasks through various queues for later use
     *
     * @param tasks initial array of tasks
     * @throws UnsupportedJsonFormat in case of name or command being null
     */
    public TaskSorterService(IncomingTask[] tasks) throws UnsupportedJsonFormat {
        this.registerTasks(tasks);

        for (IncomingTask incomingTask : tasks) {
            this.orderTasks(incomingTask);
        }
    }

    /**
     * Executes required tasks for given task then the task itself
     *
     * @param currentIncomingTask current task
     */
    private void orderTasks(IncomingTask currentIncomingTask) {
        if (done.contains(currentIncomingTask.getName())) {
            return;
        }

        for (String nameOfTask : currentIncomingTask.getRequires()) {
            this.orderTasks(allTasks.get(nameOfTask));
        }

        sortedTasks.add(currentIncomingTask);
        outgoingTasks.add(new OutgoingTask(currentIncomingTask.getName(), currentIncomingTask.getCommand()));
        done.add(currentIncomingTask.getName());
    }

    /**
     * Cycle through all tasks, checks if they have valid name and command field, and puts their names in queue
     * @param tasks array of tasks
     * @throws UnsupportedJsonFormat
     */
    private void registerTasks(IncomingTask[] tasks) throws UnsupportedJsonFormat {
        for (IncomingTask t : tasks) {
            if (t.getName() == null || t.getName().equals("") || t.getCommand() == null || t.getCommand().equals("")) {
                throw new UnsupportedJsonFormat("Unsupported json format. Tasks need to have valid 'name' and 'command'");
            }
            allTasks.put(t.getName(), t);
        }
    }
}
