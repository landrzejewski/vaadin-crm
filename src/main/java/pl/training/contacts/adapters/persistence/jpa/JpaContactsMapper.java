package pl.training.contacts.adapters.persistence.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.training.commons.ResultPage;
import pl.training.contacts.domain.Contact;

@Mapper(componentModel = "spring", uses = JpaAddressMapper.class)
public interface JpaContactsMapper {

    ContactEntity toEntity(Contact contact);

    Contact toDomain(ContactEntity contactEntity);

    @Mapping(source = "content", target = "elements")
    ResultPage<Contact> toDomain(Page<ContactEntity> page);

    /*default ResultPage<Contact> toDomain(Page<ContactEntity> page) {
        var contacts = page.getContent()
                .stream()
                .map(this::toDomain)
                .toList();
        return new ResultPage<>(contacts, page.getTotalPages());
    }*/

}
