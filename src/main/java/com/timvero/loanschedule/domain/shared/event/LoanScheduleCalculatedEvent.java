package com.timvero.loanschedule.domain.shared.event;


public record LoanScheduleCalculatedEvent(String loanId) implements LoanScheduleEvent {

}
