package pl.training.contacts.adapters.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaContactsRepository extends JpaRepository<ContactEntity, String> {
}
