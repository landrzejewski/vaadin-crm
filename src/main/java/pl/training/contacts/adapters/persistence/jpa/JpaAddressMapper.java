package pl.training.contacts.adapters.persistence.jpa;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.contacts.domain.Address;

@Mapper(componentModel = "spring")
public interface JpaAddressMapper {

    @Mapping(source = "baseInfo", target = "base")
    AddressEntity toEntity(Address address);

    @Mapping(source = "base", target = "baseInfo")
    Address toDomain(AddressEntity addressEntity);

}
