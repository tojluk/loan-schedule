package com.timvero.loanschedule.domain.shared.port.out.event;


public record CalculatedDomainEvent(long loanId) implements DomainEvent {

}
