package gr.pwc.assignment.authorizedpersonnel.service;

import gr.pwc.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;
import gr.pwc.assignment.authorizedpersonnel.repository.AuthorizedPersonnelRepository;
import gr.pwc.assignment.common.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthorizedPersonnelRepository authorizedPersonnelRepository;

    public CustomUserDetailsService(AuthorizedPersonnelRepository authorizedPersonnelRepository) {
        this.authorizedPersonnelRepository = authorizedPersonnelRepository;
    }
    @Override
    public AuthorizedPersonnel loadUserByUsername(String username)  {
        return this.authorizedPersonnelRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username" + username + "not Found"));
    }
}
