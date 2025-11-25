package gr.pwc.assignment.authorizedpersonnel.controller;

import gr.pwc.assignment.authorizedpersonnel.model.dto.AuthorizedPersonnelDto;
import gr.pwc.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;
import gr.pwc.assignment.authorizedpersonnel.service.AdminManagementService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {
    Logger log = LoggerFactory.getLogger(AdminController.class);

    private final AdminManagementService adminManagement;


    public AdminController(AdminManagementService adminManagement) {
        this.adminManagement = adminManagement;
    }


    @PostMapping("/authPersonnel")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorizedPersonnelDto createAuthPersonnel(@RequestBody @Valid AuthorizedPersonnel authorizedPersonnel) {
        return this.adminManagement.createAuthPersonnel(authorizedPersonnel);
    }

    @GetMapping("/authPersonnel")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorizedPersonnelDto> fetchAllAuthPersonnel() {
        return this.adminManagement.fetchAllAuthPersonnel();
    }

    @DeleteMapping("/authPersonnel/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthPersonnel(@PathVariable @NotBlank String username) {
        this.adminManagement.deleteAuthPersonnel(username);
    }

}
