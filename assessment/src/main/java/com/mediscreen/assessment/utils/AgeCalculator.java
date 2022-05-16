package com.mediscreen.assessment.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
@Slf4j
public class AgeCalculator {

    public int calculateAge(final LocalDate birthdate) {
        log.debug("start calculateAge");
        try{
            LocalDate currentDate = LocalDate.now();
            if(birthdate.isAfter(currentDate)) {
                log.error("Birthday after the current date");
                throw new IllegalArgumentException("BirthDay Date format");
            }
            log.debug("end calculateAge");
            return Period.between(birthdate, currentDate).getYears();
        }catch(Exception e) {
            System.out.println(birthdate);
            log.error("Person format date invalid");
            throw new IllegalArgumentException("BirthDay Date format");
        }
    }
}
