package com.project.moneytransfer.Controller;

import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
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
        try {
            AccountDto account = accountService.getAccount(accountId);
            return ResponseEntity.status(OK)
                    .body(new ApiResponse("Success", account));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{accountId}/getAllTransactions")
    public ResponseEntity<ApiResponse> getTransactionsByAccountId(@PathVariable Long accountId) {
        try {
            List<TransactionDto> transactions = accountService.getTransactionsByAccountId(accountId);
            if(transactions.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("NO DATA", null));
            }
            return ResponseEntity.status(OK)
                    .body(new ApiResponse("Success", transactions));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/getAllAccounts")
    public ResponseEntity<ApiResponse> getAllAccounts() {
        try {
            List<AccountDto> accounts = accountService.getAllAccounts();
            if(accounts.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("NO DATA", null));
            }
            return ResponseEntity.status(OK)
                    .body(new ApiResponse("Success", accounts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/createAccount")
    public ResponseEntity<ApiResponse> createAccount(@RequestParam Long customerId) {
        try {
            accountService.addNewAccount(customerId);
            return ResponseEntity.status(OK)
                    .body(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/blockAccount")
    public ResponseEntity<ApiResponse> blockAccount(@RequestParam Long accountId) {
        try {
            accountService.blockAccount(accountId);
            return ResponseEntity.status(OK)
                    .body(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }/* catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }*/
    }

    @PutMapping("/unblockAccount")
    public ResponseEntity<ApiResponse> unblockAccount(@RequestParam Long accountId) {
        try {
            accountService.unblockAccount(accountId);
            return ResponseEntity.status(OK)
                    .body(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
