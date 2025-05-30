package icu.xiii.app.repository.contact;

import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import icu.xiii.app.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends BaseRepository<Contact, ContactDtoRequest> {

    void save(ContactDtoRequest request);

    Optional<List<Contact>> getAll();

    Optional<Contact> getById(Long id);

    void update(Long id, ContactDtoRequest request);

    boolean deleteById(Long id);

    Optional<Contact> getLastEntity();
}
