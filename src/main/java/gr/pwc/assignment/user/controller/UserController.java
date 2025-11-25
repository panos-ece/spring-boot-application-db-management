package gr.pwc.assignment.user.controller;

import gr.pwc.assignment.user.model.dto.UserDto;
import gr.pwc.assignment.user.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
public class UserController {

    private final UserService userSvc;

    public UserController(UserService userSvc) {
        this.userSvc = userSvc;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return this.userSvc.fetchAllUsersInformation();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable @NotNull UUID id) {
        return  this.userSvc.fetchUserInformationById(id);
    }

    @GetMapping("/users/search")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsersByName(@RequestParam @NotBlank String name) {
        return this.userSvc.fetchUserInformationByName(name);
    }
}
