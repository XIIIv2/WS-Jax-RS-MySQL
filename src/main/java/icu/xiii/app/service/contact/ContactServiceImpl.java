package icu.xiii.app.service.contact;

import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import icu.xiii.app.repository.contact.ContactRepository;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.Objects;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    @Inject
    private ContactRepository repository;

    @Override
    public Contact create(ContactDtoRequest request) {
        Objects.requireNonNull(request, "Parameter [request] must not be null!");
        repository.save(request);
        return repository
                .getLastEntity()
                .orElse(null);
    }

    @Override
    public List<Contact> getAll() {
        return repository
                .getAll()
                .orElse(Collections.emptyList());
    }

    @Override
    public Contact getById(Long id) {
        Objects.requireNonNull(id, "Parameter [id] must not be null!");
        return repository
                .getById(id)
                .orElse(null);
    }

    @Override
    public Contact update(Long id, ContactDtoRequest request) {
        Objects.requireNonNull(request, "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        if (repository.getById(id).isPresent()) {
            repository.update(id, request);
        }
        return repository
                .getById(id)
                .orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id, "Parameter [id] must not be null!");
        return repository.deleteById(id);
    }
}
