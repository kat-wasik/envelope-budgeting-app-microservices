package com.example.feignservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private Long id;
    private String name;
    private String balance;
    private String type;
    private Long budget;
   }
