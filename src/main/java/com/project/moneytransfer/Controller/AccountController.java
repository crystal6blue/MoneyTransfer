package com.project.moneytransfer.Controller;

import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Response.ApiResponse;
import com.project.moneytransfer.Service.AccountService.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountId}/get")
    public ResponseEntity<ApiResponse> getAccount(@PathVariable Long accountId) {
        AccountDto account = accountService.getAccount(accountId);

        return ResponseEntity.status(OK)
            .body(new ApiResponse("The account successfully retrieved", account));
    }

    @GetMapping("/{accountId}/getAllTransactions")
    public ResponseEntity<ApiResponse> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<TransactionDto> transactions = accountService.getTransactionsByAccountId(accountId);

        if(transactions.isEmpty()){
            return ResponseEntity.status(NOT_FOUND)
                .body(new ApiResponse("No data found", null));
        }

        return ResponseEntity.status(OK)
            .body(new ApiResponse("The account's transactions have been retrieved", transactions));
    }

    @GetMapping("/getAllAccounts")
    public ResponseEntity<ApiResponse> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();

        if(accounts.isEmpty()){
            return ResponseEntity.status(NOT_FOUND)
                .body(new ApiResponse("No data found", null));
        }

        return ResponseEntity.status(OK)
            .body(new ApiResponse("All accounts have been retrieved", accounts));
    }

    @PostMapping("/createAccount")
    public ResponseEntity<ApiResponse> createAccount(@RequestParam Long customerId) {
        accountService.addNewAccount(customerId);

        return ResponseEntity.status(CREATED)
            .body(new ApiResponse("The account has been successfully created", null));
    }

    @PutMapping("/blockAccount")
    public ResponseEntity<ApiResponse> blockAccount(@RequestParam Long accountId) {
        accountService.blockAccount(accountId);

        return ResponseEntity.status(ACCEPTED)
            .body(new ApiResponse("The account has been successfully blocked", null));
    }

    @PutMapping("/unblockAccount")
    public ResponseEntity<ApiResponse> unblockAccount(@RequestParam Long accountId) {
        accountService.unblockAccount(accountId);

        return ResponseEntity.status(ACCEPTED)
            .body(new ApiResponse("The account has been successfully unblocked", null));
    }
}
