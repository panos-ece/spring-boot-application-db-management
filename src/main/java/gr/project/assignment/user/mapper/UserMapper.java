package gr.project.assignment.user.mapper;

import org.springframework.stereotype.Component;

import gr.project.assignment.user.model.dto.UserDto;
import gr.project.assignment.user.model.entity.User;

@Component
public class UserMapper {
    public UserDto toDto(User user) {

        return new UserDto(user.getName(), user.getVat(), user.getAddress(), user.getPostCode(),
                user.getPhone(), user.getDateOfBirth(), user.getBalance());
    }

    public User toUser(UserDto userDto) {

        return new User.Builder()
                .withName(userDto.name())
                .withVat(userDto.vat())
                .withAddress(userDto.address())
                .withPostCode(userDto.postCode())
                .withPhone(userDto.phone())
                .withDateOfBirth(userDto.dateOfBirth())
                .withBalance(userDto.balance())
                .build();
    }
}
