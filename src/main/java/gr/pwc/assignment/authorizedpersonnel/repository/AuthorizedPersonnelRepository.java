package gr.pwc.assignment.authorizedpersonnel.repository;

import gr.pwc.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthorizedPersonnelRepository extends JpaRepository<AuthorizedPersonnel, String> {
    Optional<AuthorizedPersonnel> findByUsername(String username);
    void deleteByUsername(String username);
}
