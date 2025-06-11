package com.timvero.loanschedule.domain.shared.port.out.schedule;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class NotifyDomainScheduledTask implements DomainScheduledTask {

    private final long loanId;
    private final long delayInSeconds;

    @Override
    public long getDelayInSeconds() {
        return delayInSeconds;
    }
}
