package icu.xiii.app.config;

import jakarta.ws.rs.ApplicationPath;
import icu.xiii.app.controller.ContactController;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/v1")
public class AppResourceConfig extends ResourceConfig {

    public AppResourceConfig() {
        register(ContactController.class);
        register(ApplicationBinder.class);
    }
}
