package com.example.feignservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetDTO {
    Long id;
    Long user;
    List<Long> accounts;
}
