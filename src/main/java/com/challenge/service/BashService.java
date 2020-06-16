package com.challenge.service;

import com.challenge.model.OutgoingTask;

import java.util.Queue;

public class BashService {

    /**
     * Generates bash script
     *
     * @param tasks ordered tasks
     * @return bash script string
     */
    public String generateBashScript(Queue<OutgoingTask> tasks) {
        StringBuilder script = new StringBuilder();

        for (OutgoingTask t : tasks) {
            script.append(t.getCommand()).append("\n");
        }

        return script.toString();
    }
}
