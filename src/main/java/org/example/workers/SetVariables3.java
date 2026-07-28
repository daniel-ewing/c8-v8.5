package org.example.workers;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.example.Application.isLogJobEnabled;
import static org.example.configuration.JavaTimeConfigurator.getDateTime;
import static org.example.utilities.Loggers.logJob;

@Component
@Slf4j
public class SetVariables3 {

    @JobWorker(type = "setVariables3")
    public Map<String, Object> handleSetVariables3(final ActivatedJob job) {
        final String methodName = "handleSetVariables3";

        if (log.isDebugEnabled()) log.debug("-----> {}: Enter job {} of instance {}",  methodName, job.getKey(), job.getProcessInstanceKey());
        if (isLogJobEnabled) logJob(methodName, job, null);

        Map<String, Object> variablesMap = new HashMap<>();
        variablesMap.put("aBoolean1", true);
        variablesMap.put("aDate1", getDateTime());

        if (log.isDebugEnabled()) log.debug("-----> {}: Exit job {} of instance {}",  methodName, job.getKey(), job.getProcessInstanceKey());
        return variablesMap;
    }
}
