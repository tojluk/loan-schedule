package com.timvero.loanschedule.infra.adaptor.out;

import com.timvero.loanschedule.domain.shared.event.LoanScheduleEvent;
import com.timvero.loanschedule.domain.shared.port.out.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventPublisherAdaptor implements DomainEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishLoanScheduleEvent(LoanScheduleEvent event) {
        eventPublisher.publishEvent(event);
    }

}
