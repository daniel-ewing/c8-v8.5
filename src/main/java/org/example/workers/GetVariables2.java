package org.example.workers;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.example.Application.isLogJobEnabled;
import static org.example.utilities.Loggers.logJob;

@Component
@Slf4j
public class GetVariables2 {

    @JobWorker(type = "getVariables2")
    public void handleGetVariables2(final ActivatedJob job) {
        final String methodName = "handleGetVariables2";

        if (log.isDebugEnabled()) log.debug("-----> {}: Enter job {} of instance {}",  methodName, job.getKey(), job.getProcessInstanceKey());
        if (isLogJobEnabled) logJob(methodName, job, null);

        Long aLong1 = (Long)job.getVariablesAsMap().get("aLong1");
        Double aDouble1 = (Double)job.getVariablesAsMap().get("aDouble1");
        if (log.isDebugEnabled()) log.debug("-----> {}: aLong1 = {}, aDouble1 = {}", methodName, aLong1, aDouble1);

        if (log.isDebugEnabled()) log.debug("-----> {}: Exit job {} of instance {}",  methodName, job.getKey(), job.getProcessInstanceKey());
    }
}
