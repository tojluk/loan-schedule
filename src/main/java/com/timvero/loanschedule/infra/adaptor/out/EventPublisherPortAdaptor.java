package com.timvero.loanschedule.infra.adaptor.out;

import com.timvero.loanschedule.domain.shared.port.out.event.DomainEvent;
import com.timvero.loanschedule.domain.shared.port.out.event.DomainEventPublisherPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventPublisherPortAdaptor implements DomainEventPublisherPort {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishLoanScheduleEvent(DomainEvent event) {
        eventPublisher.publishEvent(event);
    }

}
