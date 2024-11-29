package com.mongodb.mongodbtest.Service;
import com.mongodb.mongodbtest.Model.Transaction;
import com.mongodb.mongodbtest.Repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}