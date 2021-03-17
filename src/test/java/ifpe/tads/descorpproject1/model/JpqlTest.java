/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.Condition;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author bruno
 */
public class JpqlTest extends AbstractBasicTest {
    
    @Test
    public void countBookByAuthor() {
        String jpql = "SELECT COUNT(b) FROM Book b WHERE b.author.name = :name";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("name", "Clarice Lispector");
        
        Long books = query.getSingleResult();
        
        assertEquals(4, books.longValue());
        
        query = em.createQuery(jpql, Long.class);
        query.setParameter("name", "Guimarães Rosa");
        
        books = query.getSingleResult();
        
        assertNotEquals(4, books.longValue());
    }
    
    @Test
    public void newestAndOldestBook() {
        String jpql = "SELECT MAX(b.releaseYear), MIN(b.releaseYear) FROM Book b";
        Query query = em.createQuery(jpql);
        
        Object[] result = (Object[]) query.getSingleResult();
        assertEquals(2003, result[0]);
        assertEquals(1943, result[1]);
    }
    
    @Test
    public void libraryWithMoreBooks() {
        String jpql = "SELECT l FROM Library l ORDER BY SIZE(l.books) DESC, l.id ASC";
        TypedQuery<Library> query = em.createQuery(jpql, Library.class);
        
        List<Library> libraries = query.getResultList();
        
        assertEquals("Cultura", libraries.get(0).getName());        
        assertEquals("Livraria Saraiva", libraries.get(1).getName());        
        assertEquals("Casa da cultura", libraries.get(2).getName());
    }
    
    @Test
    public void countBooksByConditionAndYear() {
        int year = 1960;
        
        String jpql = "SELECT COUNT(b) FROM Book b WHERE b.condition = :condition AND b.releaseYear > :year";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("condition", Condition.MANIPULATED);
        query.setParameter("year", year);
        
        Long books = query.getSingleResult();
        
        assertEquals(6, books.longValue());
    }
    
    @Test
    public void countBooksOfLibrary() {
        Long idLivraria = 1L;
        
        String jpql = "SELECT COUNT(l.books) FROM Library l WHERE l.id = ?1";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter(1, idLivraria);
        
        Long quantidade = query.getSingleResult();
        
        assertEquals(10, quantidade.longValue());
    }
    
    @Test
    public void libraryHasBook() {
        String bookTitle = "Fome: um tema proibido - últimos escritos de Josué de Castro.";
        TypedQuery<Library> query = em.createNamedQuery("Library.PorTitulo", Library.class);
        query.setParameter("bookTitle", bookTitle);
        
        List<Library> libraries = query.getResultList();
        
        assertEquals(2, libraries.size());
        assertEquals("Livraria Saraiva", libraries.get(0).getName());
        assertEquals("Cultura", libraries.get(1).getName());
    }
}
