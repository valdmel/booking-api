package com.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
}