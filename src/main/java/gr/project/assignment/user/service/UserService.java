package gr.project.assignment.user.service;

import java.util.List;

import java.util.UUID;

import gr.project.assignment.user.model.dto.UserDto;

public interface UserService {
    UserDto fetchUserInformationById(UUID id);

    List<UserDto> fetchUserInformationByName(String name);

    List<UserDto> fetchAllUsersInformation();
}
