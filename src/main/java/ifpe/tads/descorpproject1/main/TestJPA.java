/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.main;

import ifpe.tads.descorpproject1.model.Book;
import ifpe.tads.descorpproject1.model.Library;
import ifpe.tads.descorpproject1.model.Author;
import ifpe.tads.descorpproject1.model.Manager;
import ifpe.tads.descorpproject1.model.Seller;
import java.util.Date;
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
            createLibraryWithBook();
            createBookWithAuthor();
            createSeller();
            createManager();
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
        
        Book book2 = new Book();
        book2.setTitle("Tres Buracos");
        book2.setReleaseYear(2019);
        book2.setPublisher("Mino");
        
        try {
            et.begin();
            em.persist(book1);
            em.persist(book2);
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

    private static void createLibraryWithBook() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        Library library = new Library();
        Book book1 = new Book();
        
      
        try {
            et.begin();                        
            library.setName("Sebo Visconde de Sabugosa");
            Query query = em.createQuery("SELECT b FROM Book b");
            List<Book> books = query.getResultList();
            books.forEach(book -> {
                library.addBook(book);
            });
            
            book1.setTitle("Akira");
            book1.setReleaseYear(1973);
            book1.setPublisher("JBC");
            
            Author author = new Author();
            author.setName("Monteiro Lobinho");
            
            book1.setAuthor(author);
            
            library.addBook(book1);
            
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
    
    private static void createBookWithAuthor(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        Book book1 = new Book();
        book1.setTitle("Scala");
        book1.setReleaseYear(2016);
        book1.setPublisher("Casa do codigo");
        
        Author author = new Author();
        author.setName("Paulo Siqueira");
        
        book1.setAuthor(author);
        
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
    
    private static void createSeller(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        Seller seller = new Seller();
        seller.setBirthDay(new Date());
        seller.setLegalDocument("705.820.634-18");
        seller.setName("Arthur");
        seller.setPayment(1500.00);
        seller.setArea("Jogos");
        seller.addPhone("88888-8888");
        seller.addPhone("99999-9999");
        
        Seller seller2 = new Seller();
        seller2.setBirthDay(new Date());
        seller2.setLegalDocument("22222222222");
        seller2.setName("Arthur2");
        seller2.setArea("Quadrinhos");
        seller2.setPayment(1800.00);
        seller2.addPhone("11111-1111");
        
        Library library = new Library();
        library.setName("Sebo que arthur trabalha");
        
        seller.setLibrary(library);
        seller2.setLibrary(library);
        
        try {
            et.begin();                        
            
            em.persist(seller);
            em.persist(seller2);
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

    private static void createManager(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        Manager manager = new Manager();
        manager.setBirthDay(new Date());
        manager.setLegalDocument("705.820.634-18");
        manager.setName("Arthur");
        manager.setPayment(1500.00);
        manager.addPhone("55555-5555");
        
        Library library = new Library();
        library.setName("Sebo do manager");
        
        manager.setLibrary(library);
        manager.setLibrary(library);
        try {
            et.begin();                        
            
            em.persist(manager);
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
