package pl.training.contacts.domain;

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
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private ContactStatus status;

}
