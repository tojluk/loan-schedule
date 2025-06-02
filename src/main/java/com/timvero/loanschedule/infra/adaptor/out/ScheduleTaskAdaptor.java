package com.timvero.loanschedule.infra.adaptor.out;

import com.timvero.loanschedule.domain.shared.port.out.schedule.DomainScheduleTaskPort;
import com.timvero.loanschedule.domain.shared.port.out.schedule.DomainScheduledTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Slf4j
public class ScheduleTaskAdaptor implements DomainScheduleTaskPort {

    private final TaskScheduler taskScheduler;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void schedule(DomainScheduledTask domainScheduleTask) {
        taskScheduler.schedule(() -> eventPublisher.publishEvent(domainScheduleTask),
                               getCronTrigger(domainScheduleTask.getDelayInSeconds()));
    }

    private CronTrigger getCronTrigger(long delaySeconds) {
        LocalDateTime futureTime = LocalDateTime.now().plusSeconds(delaySeconds);
        return new CronTrigger(String.format("%d %d %d %d %d ?",
                                             futureTime.getSecond(),
                                             futureTime.getMinute(),
                                             futureTime.getHour(),
                                             futureTime.getDayOfMonth(),
                                             futureTime.getMonthValue()));
    }

}
