/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.startsWith;
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
        
        String jpql = "SELECT b FROM Book b WHERE b.id = ?1";
        TypedQuery<Book> query = em.createQuery(jpql, Book.class);
        query.setParameter(1, 1L);
        
        Book book = query.getSingleResult();
        author.addBook(book);
        em.persist(author);
        em.flush();
        
        assertNotNull(author.getId());
        assertEquals(1, author.getBooks().size());
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
    public void countAuthorBooks() {
        String jpql = "SELECT a FROM Author a WHERE a.id = ?1";
        
        TypedQuery<Author> query = em.createQuery(jpql, Author.class);
        query.setParameter(1, 1L);
        
        Author author = query.getSingleResult();
        assertNotNull(author);
        
        assertEquals(4, author.getBooks().size());
    }
    
    @Test
    public void readAuthor() {
        String jpql = "SELECT a FROM Author a WHERE a.id = ?1";
        TypedQuery<Author> query = em.createQuery(jpql, Author.class);
        query.setParameter(1, 1L);
        
        Author author = query.getSingleResult();
        
        assertEquals("Jorge Amado", author.getName());
    }
    
    @Test
    public void updateAuthor() {
        String editado = " [EDITADO]";
        String jpql = "SELECT a FROM Author a WHERE a.id = ?1";
        
        TypedQuery<Author> query = em.createQuery(jpql, Author.class);
        query.setParameter(1, 3L);
        
        Author author = query.getSingleResult();
        assertNotNull(author);
        String tituloEditado = author.getName() + editado;
        
        author.setName(tituloEditado);        
        em.merge(author);
        em.flush();
        
        author = query.getSingleResult();
        
        assertEquals(tituloEditado, author.getName());
    }
    /*
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
    */
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirAutorInvalido() {
        Author author = new Author();
        try {
            author.setName("");
    
            String jpql = "SELECT b FROM Book b WHERE b.id = ?1";
            TypedQuery<Book> query = em.createQuery(jpql, Book.class);
            query.setParameter(1, 1L);
    
            Book book = query.getSingleResult();
            author.addBook(book);
            em.persist(author);
            em.flush();
    
            assertNotNull(author.getId());
            assertEquals(1, author.getBooks().size());
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            
            
            for (ConstraintViolation violation: constraintViolations) {
                assertThat(violation.getMessage(),
                    CoreMatchers.anyOf(
                        startsWith("O Nome do autor deve ser valido")
                    ));
            }
            
            assertEquals(1, constraintViolations.size());
            assertNull(author.getId());
            throw ex;
        }
    }
}
