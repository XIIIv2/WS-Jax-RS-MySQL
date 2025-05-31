package icu.xiii.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "contact_fields")
public class ContactField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "contact_id", nullable = false)
    @JsonBackReference
    private Contact contact;

    @Column(name = "field_name")
    private String name;

    @Column(name = "field_value")
    private String value;

    public ContactField() {

    }

    public ContactField(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public ContactField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ContactField that = (ContactField) o;
        return Objects.equals(id, that.id) && name.equals(that.name) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + name.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ContactField{" +
                "id='" + id + "'" +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
