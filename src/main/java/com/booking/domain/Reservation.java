package com.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public boolean canBeCompleted() {
        return isDateValid() && isNotInAdvance() && isNotLongerThanPermitted();
    }

    private boolean isNotLongerThanPermitted() {
        return startDate.until(endDate, ChronoUnit.DAYS) <= 3;
    }

    private boolean isNotInAdvance() {
        LocalDate today = LocalDate.now();

        return today.until(startDate, ChronoUnit.DAYS) < 30;
    }

    private boolean isDateValid() {
        return startDate.isBefore(endDate);
    }
}