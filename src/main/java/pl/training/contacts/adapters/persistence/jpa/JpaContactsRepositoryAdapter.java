package pl.training.contacts.adapters.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import pl.training.commons.PageConfig;
import pl.training.commons.ResultPage;
import pl.training.contacts.domain.Contact;
import pl.training.contacts.ports.ContactsRepository;

@Profile("dev")
@Repository
@RequiredArgsConstructor
public class JpaContactsRepositoryAdapter implements ContactsRepository {

    private final JpaContactsRepository contacts;
    private final JpaContactsMapper mapper;

    @Override
    public ResultPage<Contact> findAll(PageConfig pageConfig) {
        var pageRequest = PageRequest.of(pageConfig.index(), pageConfig.size());
        var page = contacts.findAll(pageRequest);
        return mapper.toDomain(page);
    }

    @Override
    public void deleteById(String id) {
        contacts.deleteById(id);
    }

    @Override
    public Contact save(Contact contact) {
        var contactEntity = mapper.toEntity(contact);
        return mapper.toDomain(contacts.save(contactEntity));
    }

}
