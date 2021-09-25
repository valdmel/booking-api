package com.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDTO {

    @NotNull(message = "Start date cannot be null!")
    @JsonProperty("start_date")
    private String startDate;

    @NotNull(message = "End date cannot be null!")
    @JsonProperty("end_date")
    private String endDate;
}