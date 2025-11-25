package gr.pwc.assignment.authorizedpersonnel.service;

import gr.pwc.assignment.authorizedpersonnel.model.dto.AuthorizedPersonnelDto;
import gr.pwc.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;
import gr.pwc.assignment.authorizedpersonnel.repository.AuthorizedPersonnelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminManagementServiceTest {

    @Mock
    private AuthorizedPersonnelRepository authRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminManagementServiceImpl adminService;

    @Test
    void createAuthPersonnel_shouldEncodePasswordAndSave() {
//        AuthorizedPersonnel admin = new AuthorizedPersonnel.Builder()
//                                                                    .withName("Balance Admin")
//                                                                    .withUsername("balance_admin")
//                                                                    .withPassword("superAdmin123!@#")
//                                                                    .withRole("ADMIN").build();
//
//        AuthorizedPersonnel input = new AuthorizedPersonnel.Builder()
//                                                                    .withName("jim")
//                                                                    .withUsername("jim1")
//                                                                    .withPassword("rawpass")
//                                                                    .withRole("ADMIN").build();
//
//        AuthorizedPersonnel saved = new AuthorizedPersonnel.Builder()
//                                                                    .withName("jim")
//                                                                    .withUsername("jim1")
//                                                                    .withPassword("encodedpass")
//                                                                    .withRole("ADMIN").build();
//
//        when(passwordEncoder.encode("rawpass")).thenReturn("encodedpass");
//        when(authRepo.save(any(AuthorizedPersonnel.class))).thenReturn(saved);
//        when(SecurityContextHolder.getContext()).thenReturn(Mockito.mock(SecurityContext.class));
//        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(Mockito.mock(UsernamePasswordAuthenticationToken.class));
//        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("Balance Admin");
////        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(new
////                UsernamePasswordAuthenticationToken(admin,null,admin.getAuthorities()));
////        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("me");
//
//        AuthorizedPersonnelDto result = adminService.createAuthPersonnel(input);
//
//        assertThat(result.password()).isEqualTo("encodedpass");
//        verify(authRepo, times(1)).save(input);
    }
}
