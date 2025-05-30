package icu.xiii.app.repository.contact;

import icu.xiii.app.config.HibernateUtil;
import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ContactRepositoryImpl implements ContactRepository {

    @Override
    public void save(ContactDtoRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Contact contact = new Contact(request);
            session.persist(contact);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<List<Contact>> getAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Contact> contacts = session
                    .createQuery("FROM Contact", Contact.class)
                    .getResultList();
            transaction.commit();
            return Optional.of(contacts);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Contact> getById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Contact contact = session.find(Contact.class, id);
            transaction.commit();
            return Optional.ofNullable(contact);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Long id, ContactDtoRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Contact contact = new Contact(request);
            contact.setId(id);
            //session.persist(contact);
            session.merge(contact);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Optional<Contact> contact = getById(id);
            if (contact.isEmpty()) {
                return false;
            }
            session.remove(contact.get());
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public Optional<Contact> getLastEntity() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TypedQuery<Contact> query = session
                    .createQuery("FROM Contact ORDER BY id DESC", Contact.class);
            query.setMaxResults(1);
            Contact contact = query.getSingleResult();
            transaction.commit();
            return Optional.of(contact);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
