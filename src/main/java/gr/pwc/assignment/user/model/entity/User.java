package gr.pwc.assignment.user.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    private UUID userId;

    private String name;
    private String vat;

    private long counterId;

    private String address;

    private String postCode;
    private String phone;
    private LocalDate dateOfBirth;

    @NotNull(message = "Balance should not be empty")
    @Min(value = 0,message = "Balance should not be below 0.")
    private long balance = 100;

    public User(Builder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.vat = builder.vat;
        this.counterId = builder.counterId;
        this.address = builder.address;
        this.postCode = builder.postCode;
        this.phone = builder.phone;
        this.dateOfBirth = builder.dateOfBirth;
        this.balance = builder.balance;
    }

    public User() {
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getVat() {
        return vat;
    }

    public long getCounterId() {
        return counterId;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public long getBalance() {
        return balance;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setVat(String vat) {
        this.vat = vat;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public User increaseBalance(long balance) {
        this.balance += balance;
        return this;
    }

    public User decreaseBalance(long balance) {
        this.balance -= balance;
        return this;
    }



    @Override
    public String toString() {
        return "User{" +
                "user_id=" + userId +
                ", name='" + name + '\'' +
                ", vat=" + vat +
                ", counterId=" + counterId +
                ", address='" + address + '\'' +
                ", post_code='" + postCode + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", balance=" + balance +
                '}';
    }

    public static class Builder {
        private UUID userId;
        private String name;

        private String vat;
        private long counterId;

        private String address;
        private String postCode;
        private String phone;
        private LocalDate dateOfBirth;

        private long balance;

        public Builder withUserId(UUID userId) {
            this.userId = userId;
            return this;
        }
        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withVat(String vat) {
            this.vat = vat;
            return this;
        }
        public Builder withCounterId(long counterId) {
            this.counterId = counterId;
            return this;
        }
        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }
        public Builder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }
        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public Builder withBalance(long balance) {
            this.balance = balance;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
