package gr.project.assignment.authorizedpersonnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.project.assignment.authorizedpersonnel.model.entity.AuthorizedPersonnel;

import java.util.Optional;

@Repository
public interface AuthorizedPersonnelRepository extends JpaRepository<AuthorizedPersonnel, String> {
    Optional<AuthorizedPersonnel> findByUsername(String username);

    void deleteByUsername(String username);
}
