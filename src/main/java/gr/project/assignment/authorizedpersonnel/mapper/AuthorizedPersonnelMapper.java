package gr.project.assignment.authorizedpersonnel.mapper;

import org.springframework.stereotype.Component;

import gr.project.assignment.authorizedpersonnel.model.dto.AuthorizedPersonnelDto;
import gr.project.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;

@Component
public class AuthorizedPersonnelMapper {
    public AuthorizedPersonnelDto toDto(AuthorizedPersonnel personnel) {
        return new AuthorizedPersonnelDto(personnel.getName(), personnel.getUsername(), personnel.getPassword(),
                personnel.getRole());
    }
}
