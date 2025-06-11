package com.timvero.loanschedule.infra.mapper;

import com.timvero.loanschedule.application.dto.LoanRequestAppDto;
import com.timvero.loanschedule.application.dto.LoanResponseAppDto;
import com.timvero.loanschedule.infra.dto.LoanRequest;
import com.timvero.loanschedule.infra.dto.LoanResponse;
import org.mapstruct.Mapper;

/**
 * Utility class for mapping loan-related data between different layers of the application.
 * This class provides methods to convert LoanRequest to LoanCommand, LoanResultDomain to LoanScheduleCommand,
 * and LoanResultDomain to LoanResponse with associated payment details.
 */
@Mapper(componentModel = "spring")
public interface LoanInfraMapper {

    LoanRequestAppDto toLoanCommand(LoanRequest loanRequestAppDto);
    LoanResponse toLoanResponse(LoanResponseAppDto loanResponseAppDto);

}
