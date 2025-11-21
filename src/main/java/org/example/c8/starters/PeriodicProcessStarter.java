package org.example.c8.starters;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import lombok.extern.slf4j.Slf4j;
import org.example.c8.Application;
import org.example.c8.utilities.VariableGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static org.example.c8.utilities.Loggers.logInstance;

@Component
@EnableScheduling
@Slf4j
public class PeriodicProcessStarter {

    private final ZeebeClient client;

    public PeriodicProcessStarter(ZeebeClient client) {
        this.client = client;
    }


    @Scheduled(fixedRate = 60000L)
    public void startProcessInstance(){
        String methodName = "startProcessInstance";

        if (log.isDebugEnabled()) log.debug("-----> {}: Enter", methodName);
        if (!Application.isPeriodicProcessStarterEnabled) return;

        // blocking / synchronous creation of a process instance => returns an instance
        ProcessInstanceEvent processInstanceEvent = client.newCreateInstanceCommand()
                .bpmnProcessId(Application.processKey)
                .latestVersion()
                .variables(VariableGenerator.generateSimpleVariables())
                .send()
                .join();

        if (log.isDebugEnabled()) logInstance(processInstanceEvent);

        if (log.isDebugEnabled()) log.debug("-----> {}: Exit", methodName);
    }

}
