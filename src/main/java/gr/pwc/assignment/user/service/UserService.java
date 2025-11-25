package gr.pwc.assignment.user.service;

import gr.pwc.assignment.user.model.dto.UserDto;

import java.util.List;

import java.util.UUID;

public interface UserService {
    UserDto fetchUserInformationById(UUID id);
    List<UserDto>fetchUserInformationByName(String name);
    List<UserDto> fetchAllUsersInformation();
}
