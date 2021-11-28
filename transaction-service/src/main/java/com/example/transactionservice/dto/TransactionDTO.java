package com.example.transactionservice.dto;

import com.example.transactionservice.model.Money;
import lombok.*;

import javax.persistence.Embedded;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private Long id;
    private Long account;
    private String amount;
    private String date;
    private String description;
}
