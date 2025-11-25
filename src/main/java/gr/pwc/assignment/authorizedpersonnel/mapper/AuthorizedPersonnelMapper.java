package gr.pwc.assignment.authorizedpersonnel.mapper;

import gr.pwc.assignment.authorizedpersonnel.model.dto.AuthorizedPersonnelDto;
import gr.pwc.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;
import org.springframework.stereotype.Component;

@Component
public class AuthorizedPersonnelMapper {
    public AuthorizedPersonnelDto toDto(AuthorizedPersonnel personnel) {
        return new AuthorizedPersonnelDto(personnel.getName(),  personnel.getUsername(), personnel.getPassword(), personnel.getRole());
    }
}
