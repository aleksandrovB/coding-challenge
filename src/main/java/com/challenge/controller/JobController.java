package com.challenge.controller;

import com.challenge.service.BashService;
import com.challenge.service.TaskSorterService;
import com.challenge.exceptions.UnsupportedJsonFormat;
import com.challenge.model.IncomingTask;
import com.challenge.model.OutgoingTask;
import com.challenge.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
@RequestMapping("/submit")
public class JobController {

    @Autowired
    private IncomingTask incomingTask;
    private ProcessBuilder processBuilder = new ProcessBuilder();

    /**
     * Receives POST request to `/submit/tasks` with tasks in JSON format, response is ordered tasks in json format
     * @param tasks tasks in json format
     * @return tasks in order
     * @throws UnsupportedJsonFormat in case of name or command being null
     */
    @PostMapping(value = "/tasks", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Queue<OutgoingTask> receiveTasks(@RequestBody Tasks tasks) throws UnsupportedJsonFormat {
        try {
            TaskSorterService sorterService = new TaskSorterService(tasks.getTasks());
            return sorterService.getOutgoingTasks();
        } catch (NullPointerException e) {
            throw new UnsupportedJsonFormat("Unsupported json format");
        }
    }

    /**
     * Receives POST request to `/submit/bash` with tasks in JSON format, response is bash script
     * @param tasks tasks in json format
     * @return commands in plain text
     * @throws UnsupportedJsonFormat in case of name or command being null
     */
    @PostMapping(value = "/bash", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String receiveBash(@RequestBody Tasks tasks) throws UnsupportedJsonFormat {
        try {
            TaskSorterService sorterService = new TaskSorterService(tasks.getTasks());
            BashService bashService = new BashService();
            return bashService.generateBashScript(sorterService.getOutgoingTasks());
        } catch (NullPointerException e) {
            throw new UnsupportedJsonFormat("Unsupported json format");
        }
    }
}
