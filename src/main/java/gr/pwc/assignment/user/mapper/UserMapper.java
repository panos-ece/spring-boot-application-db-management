package gr.pwc.assignment.user.mapper;

import gr.pwc.assignment.user.model.dto.UserDto;
import gr.pwc.assignment.user.model.entity.User;
import org.springframework.stereotype.Component;

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
