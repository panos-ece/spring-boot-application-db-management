package gr.project.assignment.authorizedpersonnel.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import gr.project.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;
import gr.project.assignment.authorizedpersonnel.repository.AuthorizedPersonnelRepository;
import gr.project.assignment.common.exceptions.UserNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthorizedPersonnelRepository authorizedPersonnelRepository;

    public CustomUserDetailsService(AuthorizedPersonnelRepository authorizedPersonnelRepository) {
        this.authorizedPersonnelRepository = authorizedPersonnelRepository;
    }

    @Override
    public AuthorizedPersonnel loadUserByUsername(String username) {
        return this.authorizedPersonnelRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with username" + username + "not Found"));
    }
}
