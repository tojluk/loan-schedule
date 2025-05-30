package com.timvero.loanschedule.domain.shared.port.out;

import com.timvero.loanschedule.domain.shared.event.LoanScheduleEvent;

public interface DomainEventPublisher {

    void publishLoanScheduleEvent(LoanScheduleEvent event);

}
