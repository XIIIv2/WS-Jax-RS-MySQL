package icu.xiii.app.config;

import icu.xiii.app.repository.contact.ContactRepository;
import icu.xiii.app.repository.contact.ContactRepositoryImpl;
import icu.xiii.app.service.contact.ContactService;
import icu.xiii.app.service.contact.ContactServiceImpl;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

public class ApplicationBinder implements Feature {

    public boolean configure(FeatureContext context) {
        context.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(ContactRepositoryImpl.class).to(ContactRepository.class);
                bind(ContactServiceImpl.class).to(ContactService.class);
                bind(JacksonJsonProvider.class);
            }
        });
        return true;
    }
}
