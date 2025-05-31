package icu.xiii.app.entity;

import com.fasterxml.jackson.annotation.*;
import icu.xiii.app.dto.contact.ContactDtoRequest;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "contacts")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Kiev")
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on", updatable = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Kiev")
    private Instant updatedOn;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "contact", orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty("extra")
    private final Set<ExtraField> extraFields = new HashSet<>();

    public Contact() {

    }

    public Contact(ContactDtoRequest request) {
        this();
        setId(request.id());
        setName(request.name());
        setPhone(request.phone());
        setExtraFields(request.extra());
    }

    public Contact(Long id, ContactDtoRequest request) {
        this();
        setId(id);
        setName(request.name());
        setPhone(request.phone());
        this.setExtraFields(request.extra() );
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

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<ExtraField> getExtraFields() {
        return Collections.unmodifiableSet(this.extraFields);
    }

    @JsonSetter("extra")
    public void setExtraFields(Set<ExtraField> extraFields) {
        if (extraFields == null) {
            return;
        }
        extraFields.forEach(this::addExtraField);
    }

    public void addExtraField(ExtraField field) {
        field.setContact(this);
        this.extraFields.add(field);
    }

    public void removeExtraField(ExtraField field) {
        this.extraFields.remove(field);
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
                ", extraFields=" + extraFields +
                '}';
    }
}
