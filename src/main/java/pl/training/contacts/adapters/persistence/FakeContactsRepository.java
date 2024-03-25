package pl.training.contacts.adapters.persistence;

import org.springframework.stereotype.Repository;
import pl.training.commons.PageConfig;
import pl.training.commons.ResultPage;
import pl.training.contacts.domain.Address;
import pl.training.contacts.domain.Contact;
import pl.training.contacts.ports.ContactsRepository;

import java.util.List;
import java.util.UUID;

import static pl.training.contacts.domain.ContactStatus.ACTIVE;
import static pl.training.contacts.domain.ContactStatus.INACTIVE;

@Repository
public class FakeContactsRepository implements ContactsRepository {

    private static final List<Contact> CONTACTS = List.of(
            Contact.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Jan")
                    .lastName("Kowalski")
                    .email("jan@training.pl")
                    .status(ACTIVE)
                    .address(Address.builder()
                            .baseInfo("ul. Dobra 28")
                            .postalCode("61-505")
                            .city("Poznań")
                            .country("Polska")
                            .build())
                    .build(),
            Contact.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Marek")
                    .lastName("Nowak")
                    .email("marek@training.pl")
                    .status(INACTIVE)
                    .address(Address.builder()
                            .baseInfo("ul. Dobra 110")
                            .postalCode("61-505")
                            .city("Poznań")
                            .country("Polska")
                            .build())
                    .build()
    );

    @Override
    public ResultPage<Contact> findAll(PageConfig pageConfig) {
        return new ResultPage<>(CONTACTS, 1);
    }

}
