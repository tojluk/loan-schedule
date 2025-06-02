package com.timvero.loanschedule.domain.shared.port.out.event;

public interface DomainEventPublisherPort {

    void publishLoanScheduleEvent(DomainEvent event);

}
