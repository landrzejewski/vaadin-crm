package pl.training.contacts.domain;

import lombok.RequiredArgsConstructor;
import pl.training.commons.PageConfig;
import pl.training.commons.ResultPage;
import pl.training.contacts.ports.ContactsRepository;

@RequiredArgsConstructor
public class ContactsManager {

    private final ContactsRepository contactsRepository;

    public ResultPage<Contact> findAll(PageConfig pageConfig) {
        return contactsRepository.findAll(pageConfig);
    }

    public void deleteById(String id) {
        contactsRepository.deleteById(id);
    }

    public Contact save(Contact contact) {
        return contactsRepository.save(contact);
    }

}
