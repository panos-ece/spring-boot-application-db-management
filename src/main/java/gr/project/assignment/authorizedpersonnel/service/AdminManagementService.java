package gr.project.assignment.authorizedpersonnel.service;

import java.util.List;

import gr.project.assignment.authorizedpersonnel.model.dto.AuthorizedPersonnelDto;
import gr.project.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;

public interface AdminManagementService {
    public AuthorizedPersonnelDto createAuthPersonnel(AuthorizedPersonnel authorizedPersonnel);

    public List<AuthorizedPersonnelDto> fetchAllAuthPersonnel();

    public void deleteAuthPersonnel(String username);
}
