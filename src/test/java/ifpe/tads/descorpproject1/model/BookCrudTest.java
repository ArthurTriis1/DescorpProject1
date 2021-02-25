/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.util.List;
import javax.persistence.TypedQuery;
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
        String titulo = "Tieta do Agreste";
        String jpql = "SELECT b FROM Book b WHERE b.title = :titulo";
        
        TypedQuery<Book> query = em.createQuery(jpql, Book.class);
        query.setParameter("titulo", titulo);
        
        Book book = query.getSingleResult();
        
        assertNotNull(book);
    }
    
    @Test
    public void updateBook() {
        String editado = " [EDITADO]";
        Long idLivro = 1L;
        
        Book book = em.find(Book.class, idLivro);
        String tituloEditado = book.getTitle() + editado;
        String editoraEditado = book.getPublisher() + editado;
        
        book.setTitle(tituloEditado);
        book.setPublisher(editoraEditado);
        em.flush();
        
        String jpql = "SELECT b FROM Book b WHERE b.id = ?1";
        TypedQuery<Book> query = em.createQuery(jpql, Book.class);
        query.setParameter(1, idLivro);
        
        book = query.getSingleResult();
        assertEquals(tituloEditado, book.getTitle());
        assertEquals(editoraEditado, book.getPublisher());
    }
    /*
    @Test
    public void deleteBook(){
        Library library = em.find(Library.class, 1L);
        Book book = em.find(Book.class, 3L);
        library.removeBook(book);
        em.remove(book);
        Book deletedBook = em.find(Book.class, 3L);
        assertNull(deletedBook);
        
        TypedQuery<Library> libraryQuery = em.createNamedQuery("Library.PorLivro", Library.class);
        TypedQuery<Book> bookQuery = em.createNamedQuery("Book.PorId", Book.class);
        
        libraryQuery.setParameter("bookId", 3L);
        bookQuery.setParameter("bookId", 3L);
                        
        Book book = bookQuery.getSingleResult();
        assertNotNull(book);
           
        List<Library> libraries = libraryQuery.getResultList();
        
        libraries.forEach(library -> {
            library.removeBook(book);
        });
        em.flush();
        
        em.remove(book);
        em.flush();
        
        assertEquals(0, libraryQuery.getResultList().size());
        assertEquals(0, bookQuery.getResultList().size());
    }
    */
    
}
