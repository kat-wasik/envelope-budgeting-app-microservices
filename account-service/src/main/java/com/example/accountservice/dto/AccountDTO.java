package com.example.accountservice.dto;

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

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance='" + balance + '\'' +
                ", type='" + type + '\'' +
                ", budget=" + budget +
                '}';
    }
}