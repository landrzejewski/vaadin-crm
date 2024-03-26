package pl.training.contacts.adapters.persistence.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.training.contacts.domain.ContactStatus;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class ContactEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    private AddressEntity address;
    private ContactStatus status;

}
