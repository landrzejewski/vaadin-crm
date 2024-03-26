package pl.training.contacts;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.contacts.domain.ContactsManager;
import pl.training.contacts.ports.ContactsRepository;

@Configuration
public class ContactsConfiguration {

    @Bean
    public ContactsManager contactsManager(/*@Qualifier("jpaContactsRepositoryAdapter") */ContactsRepository contactsRepository) {
        return new ContactsManager(contactsRepository);
    }

}
