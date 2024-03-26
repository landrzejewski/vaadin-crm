package pl.training.contacts.adapters.persistence;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pl.training.commons.PageConfig;
import pl.training.commons.ResultPage;
import pl.training.contacts.domain.Address;
import pl.training.contacts.domain.Contact;
import pl.training.contacts.ports.ContactsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static pl.training.contacts.domain.ContactStatus.ACTIVE;
import static pl.training.contacts.domain.ContactStatus.INACTIVE;

@Profile("test")
@Repository
public class FakeContactsRepository implements ContactsRepository {

    private final Map<String, Contact> contacts = new HashMap<>();

    public FakeContactsRepository() {
        contacts.put("1", Contact.builder()
                .id("1")
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
                .build()
        );
        contacts.put("2", Contact.builder()
                .id("2")
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
    }

    @Override
    public ResultPage<Contact> findAll(PageConfig pageConfig) {
        var elements = new ArrayList<>(contacts.values());
        return new ResultPage<>(elements, 1);
    }

    @Override
    public void deleteById(String id) {
        contacts.remove(id);
    }

    @Override
    public Contact save(Contact contact) {
        contacts.put(contact.getId(), contact);
        return contact;
    }

}
