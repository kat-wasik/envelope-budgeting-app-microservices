package com.example.accountservice.model;

import lombok.*;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private Long budget;

    @ElementCollection
    private List<Long> transactions;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                ", budget=" + budget +
                '}';
    }
}
