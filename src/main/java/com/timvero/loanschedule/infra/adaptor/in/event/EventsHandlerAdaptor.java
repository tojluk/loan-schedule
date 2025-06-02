package com.timvero.loanschedule.infra.adaptor.in.event;

import com.timvero.loanschedule.domain.shared.port.out.event.DomainEvent;
import com.timvero.loanschedule.domain.shared.port.out.schedule.DomainScheduledTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventsHandlerAdaptor {

    @EventListener
    public void handleLoanScheduleEvent(DomainEvent event) {
        log.info(" ****** Received DomainEvent: {} ******", event);
    }

    @EventListener
    public void handleDomainScheduledTaskEvent(DomainScheduledTask event) {
        log.info(" ****** Received DomainScheduledTask: {} ******", event);
    }

}
