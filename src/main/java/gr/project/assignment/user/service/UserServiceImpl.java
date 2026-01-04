package gr.project.assignment.user.service;

import org.springframework.stereotype.Service;

import gr.project.assignment.common.exceptions.UserNotFoundException;
import gr.project.assignment.user.mapper.UserMapper;
import gr.project.assignment.user.model.dto.UserDto;
import gr.project.assignment.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public UserDto fetchUserInformationById(UUID id) {
        return this.userRepo.findById(id).map(this.userMapper::toDto).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public List<UserDto> fetchUserInformationByName(String name) {
        final var users = this.userRepo.findByName(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found with name: " + name);
        }
        return users.stream().map(this.userMapper::toDto).toList();
    }

    public List<UserDto> fetchAllUsersInformation() {
        final var userList = this.userRepo.findAll();
        return userList.stream().map(this.userMapper::toDto).toList();
    }
}
