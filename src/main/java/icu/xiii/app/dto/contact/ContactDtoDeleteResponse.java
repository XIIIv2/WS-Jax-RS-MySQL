package icu.xiii.app.dto.contact;

public record ContactDtoDeleteResponse(String message, boolean isContactDeleted) {

    public static final String SUCCESS_MESSAGE = "Contact with id %s deleted successfully!";
    public static final String FAILURE_MESSAGE = "Contact with id %s is not deleted!";

    public static ContactDtoDeleteResponse of(Long id, boolean isContactDeleted) {
        String message = FAILURE_MESSAGE.formatted(id);
        if (isContactDeleted) {
            message = SUCCESS_MESSAGE.formatted(id);
        }
        return new ContactDtoDeleteResponse(message, isContactDeleted);
    }
}

