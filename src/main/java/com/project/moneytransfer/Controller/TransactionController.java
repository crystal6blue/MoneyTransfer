package com.project.moneytransfer.Controller;

import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Exceptions.InvalidRequestException;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Request.RequestNewTransaction;
import com.project.moneytransfer.Response.ApiResponse;
import com.project.moneytransfer.Service.Transaction.ITransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/transaction")
public class TransactionController {
    private final ITransactionService transactionService;

    @GetMapping("/{transactionId}/get")
    public ResponseEntity<ApiResponse> getTransaction(@PathVariable Long transactionId) {
        try {
            TransactionDto transactionDto = transactionService.getTransactionById(transactionId);
            return ResponseEntity.status(OK)
                    .body(new ApiResponse("success", transactionDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/{accountId}/createTransaction")
    public ResponseEntity<ApiResponse> createTransaction(@Valid @RequestBody RequestNewTransaction transaction, @PathVariable Long accountId) {
        try {
            TransactionDto transactionDto = transactionService.createTransaction(transaction, accountId);
            return ResponseEntity.status(CREATED)
                    .body(new ApiResponse("success", transactionDto));
        } catch (InvalidRequestException e){
            return ResponseEntity.status(BAD_REQUEST)
                  .body(new ApiResponse(e.getMessage(), null));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), transaction));
        }
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<ApiResponse> getAllTransactions(){
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        if(transactions.isEmpty()){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse("NO DATA", null));
        }
        return ResponseEntity.status(OK)
                .body(new ApiResponse("success", transactions));
    }
}
