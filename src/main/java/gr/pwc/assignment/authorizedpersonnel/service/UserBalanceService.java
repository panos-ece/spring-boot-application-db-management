package gr.pwc.assignment.authorizedpersonnel.service;

import gr.pwc.assignment.user.model.dto.UserDto;

import java.util.UUID;

public interface UserBalanceService {
    public UserDto credit(UUID id , long amount);
    public UserDto debit(UUID id , long amount);
}
