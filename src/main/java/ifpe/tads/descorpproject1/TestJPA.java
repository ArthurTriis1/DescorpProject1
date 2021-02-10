/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author arthu
 */
public class TestJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DescorpProject1");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }
    
    public static void main(String[] args) {
        try {
            createBook();
            createAuthor();
            createLibrary();
        } finally {
            emf.close();
        }
    }

    private static void createBook() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        Book book1 = new Book();
        book1.setTitle("Eu e outras poesias");
        book1.setReleaseYear(1910);
        book1.setPublisher("Cia das letras");
        
        try {
            et.begin();
            em.persist(book1);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                Logger.getGlobal().log(Level.SEVERE,
                        "Cancelando transação com erro. Mensagem: {0}", ex.getMessage());
                et.rollback();
                Logger.getGlobal().info("Transação cancelada.");
            }
        } finally {
            em.close();
        }
    }

    private static void createAuthor() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        Author author = new Author();
      
        try {
            et.begin();                        
            author.setName("Augusto dos anjos");
            //Selecionando todas as categorias cadastradas
            Query query = em.createQuery("SELECT b FROM Book b");
            List<Book> books = query.getResultList();
            books.forEach(book -> {
                author.addBook(book);
            });
            
            em.persist(author);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                Logger.getGlobal().log(Level.SEVERE,
                        "Cancelando transação com erro. Mensagem: {0}", ex.getMessage());
                et.rollback();
                Logger.getGlobal().info("Transação cancelada.");
            }
        } finally {
            em.close();
        }   
    }

    private static void createLibrary() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        Library library = new Library();
      
        try {
            et.begin();                        
            library.setName("Sebo das artes");
            library.setAddress("Rua Dr Arapuco, 123, São José");
            //Selecionando todas as categorias cadastradas
            Query query = em.createQuery("SELECT b FROM Book b");
            List<Book> books = query.getResultList();
            books.forEach(book -> {
                library.addBook(book);
            });
            
            em.persist(library);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                Logger.getGlobal().log(Level.SEVERE,
                        "Cancelando transação com erro. Mensagem: {0}", ex.getMessage());
                et.rollback();
                Logger.getGlobal().info("Transação cancelada.");
            }
        } finally {
            em.close();
        }   
    }
}
