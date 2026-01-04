package gr.project.assignment.authorizedpersonnel.service;

import java.util.UUID;

import gr.project.assignment.user.model.dto.UserDto;

public interface UserBalanceService {
    public UserDto credit(UUID id, long amount);

    public UserDto debit(UUID id, long amount);
}
