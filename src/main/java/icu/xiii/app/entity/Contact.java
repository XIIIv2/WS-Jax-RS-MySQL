package icu.xiii.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import icu.xiii.app.dto.contact.ContactDtoRequest;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "contact", orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty("fields")
    private Set<ContactField> contactFields = new HashSet<>();

    public Contact() {

    }

    public Contact(ContactDtoRequest request) {
        setId(request.id());
        setName(request.name());
        setPhone(request.phone());
        setContactFields(request.fields());
    }

    public Contact(Long id, ContactDtoRequest request) {
        setId(id);
        setName(request.name());
        setPhone(request.phone());
        this.setContactFields(request.fields());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<ContactField> getContactFields() {
        return Collections.unmodifiableSet(this.contactFields);
    }

    public void setContactFields(Set<ContactField> contactFields) {
        contactFields.forEach(c -> {
            c.setContact(this);
        });
        this.contactFields = contactFields;
    }

    public void addContactField(ContactField field) {
        field.setContact(this);
        this.contactFields.add(field);
    }

    public void removeContactField(ContactField field) {
        field.setContact(null);
        this.contactFields.remove(field);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(name, contact.name) && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(phone);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", contactFields=" + contactFields +
                '}';
    }
}
