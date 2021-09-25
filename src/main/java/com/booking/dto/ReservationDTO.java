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
public class ReservationDTO {

    @NotNull(message = "Start date cannot be null!")
    @JsonProperty("start_date")
    private String startDate;

    @NotNull(message = "End date cannot be null!")
    @JsonProperty("end_date")
    private String endDate;

    @NotNull(message = "Customer data cannot be null!")
    @JsonProperty("customer")
    private CustomerDTO customerDTO;
}