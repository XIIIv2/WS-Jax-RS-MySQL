package icu.xiii.app.dto.contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactDtoRequest(Long id, String name, String phone) {
}
