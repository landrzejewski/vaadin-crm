package pl.training.contacts.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    private String id;
    @NotBlank
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private ContactStatus status;

    public String getCity() {
        return address != null ? address.getCity() : "";
    }

}
