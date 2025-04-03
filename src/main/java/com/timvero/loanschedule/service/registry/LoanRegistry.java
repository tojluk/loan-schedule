package com.timvero.loanschedule.service.registry;

import com.timvero.loanschedule.service.loan.LoanCalculator;
import com.timvero.loanschedule.service.type.LoanType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LoanRegistry implements SpringRegistry<LoanType, LoanCalculator> {

    private final Map<LoanType, LoanCalculator> loanTypeRegistry;

    public LoanRegistry(List<? extends LoanCalculator> loanCalculators) {
        this.loanTypeRegistry = createRegistry(loanCalculators, LoanCalculator::getLoanType);
    }

    @Override
    public LoanCalculator get(LoanType loanType) {
        if (!loanTypeRegistry.containsKey(loanType)) {
            throw new IllegalArgumentException("Unsupported loan type: " + loanType);
        }
        return loanTypeRegistry.get(loanType);
    }
}
