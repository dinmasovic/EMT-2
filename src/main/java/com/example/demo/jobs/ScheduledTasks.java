package com.example.demo.jobs;

import com.example.demo.service.domain.HostService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final HostService hostService;

    public ScheduledTasks(HostService hostService) {
        this.hostService = hostService;
    }


    @Scheduled(cron = "10 * * * * *")
    public void refreshMaterializedView() {
        System.out.println("yeah");
       this.hostService.refreshMaterializedView();
    }

}
