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
public class AuthorTest extends AbstractBasicTest{

    @Test
    public void persistirAuthorComLivro() {
        Author author = new Author();
        author.setName("Alan Moore");
        
        Book book = em.find(Book.class, 1L);
        author.addBook(book);
        em.persist(author);
        em.flush();
        assertNotNull(author.getId());
    }
    
    @Test
    public void persistirAuthorSemLivro() {
        Author author = new Author();
        author.setName("Arthur Andrade");
        em.persist(author);
        em.flush();
        assertNotNull(author.getId());
    }
    
    @Test
    public void consultarLivroDoAuthor() {
        Author author = em.find(Author.class, 2L);
        assertNotNull(author);
        assertEquals("João Cabral", author.getName());
        assertEquals(1, author.getBooks().size());
        String nameBook1 = author.getBooks().get(0).getTitle();
        assertEquals(nameBook1, "Homens e caranguejos");            
    }
}
