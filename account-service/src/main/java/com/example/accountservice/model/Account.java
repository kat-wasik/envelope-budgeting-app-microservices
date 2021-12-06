package com.example.accountservice.model;

import com.thoughtworks.xstream.converters.extended.CurrencyConverter;
import lombok.*;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Money balance;

    private Long budget;
}
