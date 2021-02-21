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
public class BookCrudTest extends AbstractBasicTest{

    @Test
    public void createBookWithAuthor() {
        Book book = new Book();
        book.setTitle("Sandman");
        book.setPublisher("Panini");
        book.setReleaseYear(1964);
        
        Author author = em.find(Author.class, 2L);
        assertEquals("João Cabral", author.getName());
        book.setAuthor(author);
        em.persist(book);
        em.flush();
        assertNotNull(book.getId());
    }
    
    @Test
    public void createBookWithoutAuthor() {
        Book book = new Book();
        book.setTitle("Astronauta");
        book.setPublisher("Panini Brasil");
        book.setReleaseYear(2016);
        
        em.persist(book);
        em.flush();
        assertNotNull(book.getId());
    }
    
    @Test
    public void createBookCreateAuthor() {
        Book book1 = new Book();
        book1.setTitle("Alias");
        book1.setReleaseYear(2008);
        book1.setPublisher("Panini Comics");

        Author author = new Author();
        author.setName("Brian Michael Bendis");

        book1.setAuthor(author);
        
        em.persist(book1);
        em.flush();
        
        assertNotNull(book1.getId());
        assertNotNull(book1.getAuthor().getId());
        assertEquals("Brian Michael Bendis", book1.getAuthor().getName());
    }
    
    @Test
    public void findBook() {
        Book book = em.find(Book.class, 2L);
        assertNotNull(book);
        assertEquals("Homens e caranguejos", book.getTitle());         
    }
    
    @Test
    public void updateBook() {
        String newTitle = "Novo titulo";
        Book book = em.find(Book.class, 1L);
        book.setTitle(newTitle);
        em.clear();        
        em.merge(book);
        em.flush();
        Book updatedBook = em.find(Book.class, 1L);
        assertEquals(newTitle, updatedBook.getTitle());         
    }
    
    @Test
    public void deleteBook(){
        Library library = em.find(Library.class, 1L);
        Book book = em.find(Book.class, 3L);
        library.removeBook(book);    
        em.remove(book);
        Book deletedBook = em.find(Book.class, 3L);
        assertNull(deletedBook);
    }
    
}
