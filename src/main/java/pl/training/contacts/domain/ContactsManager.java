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

}
