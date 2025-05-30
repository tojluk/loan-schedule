package com.timvero.loanschedule.infra.adaptor.in.event;

import com.timvero.loanschedule.domain.shared.event.LoanScheduleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventsHandlerAdaptor {

    @EventListener
    public void handleLoanScheduleEvent(LoanScheduleEvent event) {
        log.info(" ****** Received LoanScheduleEvent: {} ******", event);
    }
}
