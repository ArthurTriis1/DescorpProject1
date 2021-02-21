/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arthu
 */
public class AuthorCrudTest extends AbstractBasicTest{

    @Test
    public void createAuthorWithBook() {
        Author author = new Author();
        author.setName("Alan Moore");
        Book book = em.find(Book.class, 1L);
        author.addBook(book);
        em.persist(author);
        em.flush();
        assertNotNull(author.getId());
    }
    
    @Test
    public void createAuthorWithoutBook() {
        Author author = new Author();
        author.setName("Arthur Andrade");
        em.persist(author);
        em.flush();
        assertNotNull(author.getId());
    }
    
    @Test
    public void readAuthor() {
        Author author = em.find(Author.class, 1L);
        assertNotNull(author);
        assertEquals("Jorge Amado", author.getName());           
    }
    
    @Test
    public void updateAuthor() {
        String newName = "Cabral João";
        Author author = em.find(Author.class, 2L);
        author.setName(newName);
        em.clear();        
        em.merge(author);
        em.flush();
        Author updatedAuthor = em.find(Author.class, 2L);
        assertEquals(newName, updatedAuthor.getName());            
    }
    
    @Test
    public void deleteAuthor(){
        Library library = em.find(Library.class, 1L);
        Book book = em.find(Book.class, 3L);
        library.removeBook(book);   
        Author author = em.find(Author.class, 3L);
        em.remove(author);
        Author deletedAuthor = em.find(Author.class, 3L);
        assertNull(deletedAuthor);
    }
}
