package com.kaismemo.trade.domain.req;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CustomerQueryReq {
    @Size(min = 2, max = 32, message = "the length of name should be between 2 and 32")
    private String name;

    @Email(message = "invalid email")
    private String email;

    @Pattern(regexp = "^(Male|Female)$", message = "gender should be 'Male' or 'Female'")
    private String gender;

    @Size(min = 2, max = 64, message = "the length of country should be between 2 and 64")
    private String country;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate signupFromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate signupToDate;

    @NotNull(message = "page should not be null")
    @Min(value = 1, message = "minimal page should be less than 1")
    private Integer page;

    @NotNull(message = "page size should not be null")
    @Min(value = 1, message = "minimal page size should be less than 1")
    @Max(value = 50, message = "maximum page size should not be more than 50")
    private Integer pageSize;
}
