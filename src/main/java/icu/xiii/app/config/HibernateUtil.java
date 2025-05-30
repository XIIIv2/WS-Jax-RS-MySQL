package icu.xiii.app.config;

import icu.xiii.app.entity.Contact;
import icu.xiii.app.entity.ContactField;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {

    private static final Logger LOGGER =
            Logger.getLogger(HibernateUtil.class.getName());

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();

                configuration.addAnnotatedClass(Contact.class);
                configuration.addAnnotatedClass(ContactField.class);

                ServiceRegistry serviceRegistry =
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration
                        .buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, e.getMessage(), e);
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties props = new Properties();
        try {
            props.load(HibernateUtil.class.getResourceAsStream("/app.properties"));
            props.put(Environment.JAKARTA_JDBC_DRIVER, props.getProperty("dbDriver"));
            props.put(Environment.JAKARTA_JDBC_URL, props.getProperty("dbUrl"));
            props.put(Environment.JAKARTA_JDBC_USER, props.getProperty("dbUser"));
            props.put(Environment.JAKARTA_JDBC_PASSWORD, props.getProperty("dbPass"));
            props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            props.put("hibernate.hikari.minimumIdle", props.getProperty("minimumIdle"));
            props.put("hibernate.hikari.maximumPoolSize", props.getProperty("maximumPoolSize"));
            props.put("hibernate.hikari.idleTimeout", props.getProperty("idleTimeout"));
            props.put("hibernate.hikari.connectionTimeout", props.getProperty("connectionTimeout"));
            props.put("hibernate.connection.provider_class", props.getProperty("connectionProviderClass"));
            configuration.setProperties(props);
            return configuration;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
