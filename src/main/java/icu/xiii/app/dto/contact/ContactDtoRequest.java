package icu.xiii.app.dto.contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import icu.xiii.app.entity.ContactField;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactDtoRequest(Long id, String name, String phone, Set<ContactField> fields) {
}
