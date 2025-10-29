package com.kaismemo.trade.domain.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerVo {
    private String name;
    private String email;
    private String gender;
    private LocalDate signupDate;
    private String country;
}
