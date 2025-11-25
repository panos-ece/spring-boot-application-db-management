package gr.pwc.assignment.authorizedpersonnel.service;

import gr.pwc.assignment.authorizedpersonnel.model.dto.AuthorizedPersonnelDto;
import gr.pwc.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;

import java.util.List;

public interface AdminManagementService {
    public AuthorizedPersonnelDto createAuthPersonnel(AuthorizedPersonnel authorizedPersonnel);
    public List<AuthorizedPersonnelDto> fetchAllAuthPersonnel();
    public void deleteAuthPersonnel(String username);
}
