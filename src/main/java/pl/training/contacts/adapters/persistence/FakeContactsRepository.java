package pl.training.contacts.adapters.persistence;

import org.springframework.stereotype.Repository;
import pl.training.commons.PageConfig;
import pl.training.commons.ResultPage;
import pl.training.contacts.domain.Address;
import pl.training.contacts.domain.Contact;
import pl.training.contacts.ports.ContactsRepository;

import java.util.List;
import java.util.UUID;

@Repository
public class FakeContactsRepository implements ContactsRepository {

    private static final List<Contact> CONTACTS = List.of(
            Contact.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Jan")
                    .lastName("Kowalski")
                    .email("jan@training.pl")
                    .address(
                            new Address(
                                    "ul. Dobra 28",
                                    "",
                                    "61-505",
                                    "Poznań",
                                    "Polska")
                    )
                    .build(),
            Contact.builder()
                    .id(UUID.randomUUID().toString())
                    .firstName("Marek")
                    .lastName("Nowak")
                    .email("marek@training.pl")
                    .address(
                            new Address(
                                    "ul. Dobra 22",
                                    "",
                                    "61-505",
                                    "Poznań",
                                    "Polska")
                    )
                    .build()
    );

    @Override
    public ResultPage<Contact> findAll(PageConfig pageConfig) {
        return new ResultPage<>(CONTACTS, 1);
    }

}
