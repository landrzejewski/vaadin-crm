package pl.training.contacts.adapters.persistence.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Table(name = "addresses")
@Entity(name = "Address")
@Getter
@Setter
public class AddressEntity {

    @GeneratedValue
    @Id
    private Long id;
    @Column(name = "base", nullable = false, length = 512)
    private String base;
    private String additionalInfo;
    private String postalCode;
    private String city;
    private String country;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AddressEntity that = (AddressEntity) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
