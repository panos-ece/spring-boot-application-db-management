package gr.pwc.assignment.user.model.dto;

import java.time.LocalDate;

public record UserDto(String name, String vat ,String address, String postCode, String phone, LocalDate dateOfBirth, long balance) {
}
