package gr.project.assignment.authorizedpersonnel.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import gr.project.assignment.authorizedpersonnel.service.UserBalanceService;
import gr.project.assignment.user.model.dto.UserDto;

import java.util.UUID;

@RestController
@RequestMapping("/operator")
@Validated
public class OperatorController {
    private final UserBalanceService userBalanceService;

    public OperatorController(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    @PostMapping("/credit/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto creditUser(@PathVariable @NotNull UUID userId, @RequestParam @NotNull long amount) {
        return this.userBalanceService.credit(userId, amount);
    }

    @PostMapping("/debit/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto debitUser(@PathVariable @NotNull UUID userId, @RequestParam @NotNull long amount) {
        return this.userBalanceService.debit(userId, amount);
    }
}
