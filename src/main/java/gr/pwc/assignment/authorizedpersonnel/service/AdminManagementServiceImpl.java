package gr.pwc.assignment.authorizedpersonnel.service;

import gr.pwc.assignment.authorizedpersonnel.mapper.AuthorizedPersonnelMapper;
import gr.pwc.assignment.authorizedpersonnel.model.dto.AuthorizedPersonnelDto;
import gr.pwc.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;
import gr.pwc.assignment.authorizedpersonnel.repository.AuthorizedPersonnelRepository;
import gr.pwc.assignment.user.model.entity.User;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminManagementServiceImpl implements AdminManagementService{
    private final AuthorizedPersonnelRepository authRepo;
    private final AuthorizedPersonnelMapper  authMapper;
    private final PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger("ADMIN_AUDIT_LOGGER");

    public AdminManagementServiceImpl(AuthorizedPersonnelRepository authRepo, AuthorizedPersonnelMapper authMapper,
                                      PasswordEncoder passwordEncoder) {
        this.authRepo = authRepo;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthorizedPersonnelDto createAuthPersonnel(AuthorizedPersonnel authorizedPersonnel) {
        authorizedPersonnel.setPassword(passwordEncoder.encode(authorizedPersonnel.getPassword()));
        final var authPersonnel = this.authRepo.save(authorizedPersonnel);
        auditOperation(authPersonnel.getUsername(),"creation");

        return this.authMapper.toDto(authPersonnel);
    }

    public List<AuthorizedPersonnelDto> fetchAllAuthPersonnel() {
        return this.authRepo.findAll().stream().map(this.authMapper::toDto).toList();
    }

    @Transactional
    public void deleteAuthPersonnel(String username) {
        this.authRepo.deleteByUsername(username);
        auditOperation(username,"deletion");
    }

    private void auditOperation(String username,String action) {
        final var admin = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Admin={} action={} target={} at={}",admin,action,username,LocalDateTime.now());
    }
}
