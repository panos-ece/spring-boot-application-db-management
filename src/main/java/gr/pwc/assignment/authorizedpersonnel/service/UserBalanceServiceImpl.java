package gr.pwc.assignment.authorizedpersonnel.service;

import gr.pwc.assignment.common.exceptions.InsufficientBalanceException;
import gr.pwc.assignment.common.exceptions.InvalidRequestException;
import gr.pwc.assignment.common.exceptions.UserNotFoundException;
import gr.pwc.assignment.user.mapper.UserMapper;
import gr.pwc.assignment.user.model.dto.UserDto;
import gr.pwc.assignment.user.model.entity.User;
import gr.pwc.assignment.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserBalanceServiceImpl implements UserBalanceService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger("OPERATOR_AUDIT_LOGGER");



    public UserBalanceServiceImpl(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public UserDto credit(UUID id , long amount) {
        if (amount <= 0) throw new InvalidRequestException("Amount must be positive");

        return this.userRepo.findById(id)
                                        .map(user -> user.increaseBalance(amount))
                                        .map(this.userRepo::save)
                                        .map(user -> auditOperation(user,"increment",amount))
                                        .map(this.userMapper::toDto)
                                        .orElseThrow(
                                                () -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public UserDto debit(UUID id, long amount) {
        if (amount <= 0) throw new InvalidRequestException("Amount must be positive");

        var user = this.userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        if (user.getBalance() - amount < 0) throw new InsufficientBalanceException("Balance cannot go below 0");
        user.decreaseBalance(amount);

        user = this.userRepo.save(user);
        auditOperation(user,"decrement", amount);

        return this.userMapper.toDto(user);
    }

    private User auditOperation(User user,String action, long amount) {
        final var operator = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Operator={} performed={} on user={} amount={} newBalance={} at={}",
                operator, action, user.getName(), amount, user.getBalance(), LocalDateTime.now());
        return user;
    }
}
