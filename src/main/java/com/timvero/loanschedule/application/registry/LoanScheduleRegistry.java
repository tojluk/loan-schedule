package com.timvero.loanschedule.application.registry;

import com.timvero.loanschedule.domain.schedule.port.in.ScheduleGenerator;
import com.timvero.loanschedule.domain.schedule.model.ScheduleType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * LoanScheduleRegistry is a Spring component that implements the SpringRegistry interface
 * for managing loan schedule generators based on their schedule type.
 * It provides a method to retrieve the appropriate ScheduleGenerator based on the ScheduleType.
 */
@Component
public class LoanScheduleRegistry implements SpringRegistry<ScheduleType, ScheduleGenerator> {

    private final Map<ScheduleType, ScheduleGenerator> scheduleTypeRegistry;

    public LoanScheduleRegistry(List<? extends ScheduleGenerator> scheduleGenerators) {
        this.scheduleTypeRegistry = createRegistry(scheduleGenerators, ScheduleGenerator::getScheduleType);
    }

    @Override
    public ScheduleGenerator get(ScheduleType loanType) {
        if (!scheduleTypeRegistry.containsKey(loanType)) {
            throw new IllegalArgumentException("Unsupported loan type: " + loanType);
        }
        return scheduleTypeRegistry.get(loanType);
    }
}
