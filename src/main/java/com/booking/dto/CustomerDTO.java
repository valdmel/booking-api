package com.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @NotNull(message = "Id cannot be null!")
    @JsonProperty("id")
    private Long id;

    @NotBlank(message = "First name cannot be empty!")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty!")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "Age cannot be null!")
    @Min(1)
    @JsonProperty("age")
    private Integer age;

    @NotBlank(message = "Gender cannot be empty!")
    @JsonProperty("gender")
    private String gender;
}