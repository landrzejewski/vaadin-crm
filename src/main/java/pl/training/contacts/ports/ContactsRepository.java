package pl.training.contacts.ports;

import pl.training.commons.PageConfig;
import pl.training.commons.ResultPage;
import pl.training.contacts.domain.Contact;

public interface ContactsRepository {

    ResultPage<Contact> findAll(PageConfig pageConfig);

}
