package com.project.moneytransfer.Controller;

import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Request.RequestNewTransaction;
import com.project.moneytransfer.Response.ApiResponse;
import com.project.moneytransfer.Service.Transaction.ITransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/transaction")
public class TransactionController {
    private final ITransactionService transactionService;

    @GetMapping("/{transactionId}/get")
    public ResponseEntity<ApiResponse> getTransaction(@PathVariable Long transactionId) {
        TransactionDto transactionDto = transactionService.getTransactionById(transactionId);

        return ResponseEntity.status(OK)
            .body(new ApiResponse("The transaction has been successfully received", transactionDto));
    }

    @PostMapping("/{accountId}/createTransaction")
    public ResponseEntity<ApiResponse> createTransaction(@Valid @RequestBody RequestNewTransaction transaction, @PathVariable Long accountId) {
        TransactionDto transactionDto = transactionService.createTransaction(transaction, accountId);

        return ResponseEntity.status(CREATED)
            .body(new ApiResponse("The transaction has been successfully created", transactionDto));
    }

    @PostMapping("/transferMoneyBetweenAccounts")
    public ResponseEntity<ApiResponse> transferMoneyBetweenAccounts(
            @RequestParam Long accountId_1,
            @RequestParam Long accountId_2,
            @RequestParam BigDecimal amount){
        transactionService.transferMoneyBetweenAccounts(accountId_1, accountId_2, amount);

        return ResponseEntity.status(OK)
            .body(new ApiResponse("The money successfully has been transferred", null));
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<ApiResponse> getAllTransactions(){
        List<TransactionDto> transactions = transactionService.getAllTransactions();

        if(transactions.isEmpty()){
            return ResponseEntity.status(NOT_FOUND)
                .body(new ApiResponse("NO DATA", null));
        }

        return ResponseEntity.status(OK)
            .body(new ApiResponse("All transactions have been successfully received", transactions));
    }
}
