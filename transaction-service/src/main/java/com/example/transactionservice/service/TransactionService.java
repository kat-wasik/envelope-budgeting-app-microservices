package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransactionDTO;
import com.example.transactionservice.exception.TransactionNotFoundException;
import com.example.transactionservice.model.Money;
import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public List<TransactionDTO> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findAllByAccount(accountId)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public TransactionDTO getTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id " + id + " not found"));

        return mapToDto(transaction);
    }

    @Transactional
    public TransactionDTO save(TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.save(mapToTransaction(transactionDTO));

        return mapToDto(transaction);
    }

    @Transactional
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    @Transactional
    public void deleteTransactionsByAccount(Long accountId) {
        transactionRepository.deleteByAccount(accountId);
    }

    @Transactional
    public TransactionDTO update(TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.getById(transactionDTO.getId());
        transaction.setAmount(new Money(new BigDecimal((transactionDTO.getAmount()))));
        transaction.setDate(LocalDate.parse(transactionDTO.getDate()));
        transaction.setDescription(transactionDTO.getDescription());

        transactionRepository.save(transaction);
        return mapToDto(transaction);
    }

    private Transaction mapToTransaction(TransactionDTO transactionDTO) {
        return Transaction.builder()
                .amount(new Money((new BigDecimal(transactionDTO.getAmount())), Currency.getInstance(transactionDTO.getCurrency())))
                .date(LocalDate.parse(transactionDTO.getDate()))
                .description(transactionDTO.getDescription())
                .account(transactionDTO.getAccount())
                .build();
    }

    private TransactionDTO mapToDto(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount().getAmount().toString())
                .currency(transaction.getAmount().getCurrency().toString())
                .date(transaction.getDate().toString())
                .description(transaction.getDescription())
                .account(transaction.getAccount())
                .build();
    }

    public String calculateBalanceByAccount(Long account) {
        return transactionRepository.calculateBalanceByAccount(account).toString();
    }
}
