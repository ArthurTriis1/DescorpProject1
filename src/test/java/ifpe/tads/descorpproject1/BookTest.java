/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arthu
 */
public class BookTest extends AbstractBasicTest{

    @Test
    public void persistirBookComAuthor() {
        Book book = new Book();
        book.setTitle("Sandman");
        book.setPublisher("Panini");
        book.setReleaseYear(1964);
        
        Author author = em.find(Author.class, 2L);
        assertEquals("João Cabral", author.getName());
        book.setAuthor(author);
        em.persist(book);
        
        em.persist(book);
        em.flush();
        assertNotNull(book.getId());
    }
    
    @Test
    public void persistirBookSemAuthor() {
        Book book = new Book();
        book.setTitle("Astronauta");
        book.setPublisher("Panini Brasil");
        book.setReleaseYear(2016);
        
        em.persist(book);
        em.flush();
        assertNotNull(book.getId());
    }
    
    @Test
    public void consultarBook() {
        Book book = em.find(Book.class, 1L);
        assertEquals("Capitães da areia", book.getTitle());
        Author author = book.getAuthor();
        assertNotNull(author);
        assertEquals("Jorge Amado", author.getName());
    }
    
}
