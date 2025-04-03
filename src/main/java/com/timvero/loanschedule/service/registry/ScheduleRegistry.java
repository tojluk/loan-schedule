package com.timvero.loanschedule.service.registry;

import com.timvero.loanschedule.service.schedule.ScheduleGenerator;
import com.timvero.loanschedule.service.type.ScheduleType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ScheduleRegistry implements SpringRegistry<ScheduleGenerator, ScheduleType> {

    private final Map<ScheduleType, ScheduleGenerator> scheduleTypeRegistry;

    public ScheduleRegistry(List<? extends ScheduleGenerator> scheduleGenerators) {
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
