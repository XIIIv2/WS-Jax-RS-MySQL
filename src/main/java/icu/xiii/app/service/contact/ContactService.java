package icu.xiii.app.service.contact;

import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import icu.xiii.app.service.BaseService;

import java.util.List;

public interface ContactService extends BaseService<Contact, ContactDtoRequest> {

    Contact create(ContactDtoRequest request);

    List<Contact> getAll();

    Contact getById(Long id);

    Contact update(Long id, ContactDtoRequest request);

    boolean deleteById(Long id);
}
