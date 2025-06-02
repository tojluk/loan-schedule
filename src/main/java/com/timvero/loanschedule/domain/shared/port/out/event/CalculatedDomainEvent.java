package com.timvero.loanschedule.domain.shared.port.out.event;


public record CalculatedDomainEvent(String loanId) implements DomainEvent {

}
