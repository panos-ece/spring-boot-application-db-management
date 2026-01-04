package gr.project.assignment.authorizedpersonnel.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class AuthorizedPersonnel implements UserDetails {

    @NotNull
    private String name;

    @Id
    @Column(unique=true)
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;

    public AuthorizedPersonnel() {}

    public AuthorizedPersonnel(Builder builder) {
        this.name = builder.name;
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }


    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }


    @Override
    public String toString() {
        return "AuthorizedPersonel{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static class Builder {
        private String name;
        private String username;
        private String password;
        private String role;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }
        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder withRole(String role) {
            this.role = role;
            return this;
        }
        public AuthorizedPersonnel build() {
            return new AuthorizedPersonnel(this);
        }
    }
}
