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
public class LibraryCrudTest extends AbstractBasicTest{
    
    @Test
    public void createLibraryWithoutBook() {
        Library library = new Library();
        library.setName("Sebo bom aconchego");
        
        em.persist(library);
        em.flush();
        assertNotNull(library.getId());
    }
    
    @Test
    public void createLibraryWithBook() {
        Library library = new Library();
        library.setName("Loja do saber");
        
        Book book = new Book();
        book.setTitle("Thor: O Cerco");
        book.setPublisher("Marvel");
        book.setReleaseYear(2012);
        library.addBook(book);
        
        em.persist(library);
        em.flush();
        
        assertNotNull(library.getId());
        assertNotNull(library.getBooks().get(0).getId());
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
    public void countLibraryHasBook() {
        Long idLivro = 9L;
        
        String jpql = "SELECT COUNT(l) FROM Library l JOIN l.books book WHERE book.id = :bookId";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("bookId", idLivro);
        
        Long quantidade = query.getSingleResult();
        
        assertEquals(2, quantidade.longValue());
    }
    
    @Test
    public void updateLibrary() {
        String newName = "Mascarenhas Livros";
        
        String jpql = "SELECT l FROM Library l WHERE l.id = :libId";
        TypedQuery<Library> query = em.createQuery(jpql, Library.class);
        query.setParameter("libId", 3L);
        
        Library library = query.getSingleResult();
        
        library.setName(newName);
        
        em.flush();
        
        library = query.getSingleResult();
        
        assertEquals(newName, library.getName());
    }
    
    @Test
    public void removeBookFromLibrary() {
        Long idLivro = 9L;
        
        TypedQuery<Library> query = em.createNamedQuery("Library.PorLivro", Library.class);
        query.setParameter("bookId", idLivro);
        
        TypedQuery<Book> queryBook = em.createNamedQuery("Book.PorId", Book.class);
        queryBook.setParameter("bookId", idLivro);
        
        List<Library> libraries = query.getResultList();
        Book book = queryBook.getSingleResult();
        
        assertNotNull(book);
        
        libraries.forEach(library -> {
            library.removeBook(book);
        });
        
        em.flush();
        
        libraries = query.getResultList();
        
        assertEquals(0, libraries.size());
    }
    
    @Test
    public void removeLibrary () {
        String libName = "Casa da cultura";
        
        String jpql = "SELECT l FROM Library l WHERE l.name = ?1";
        TypedQuery<Library> query = em.createQuery(jpql, Library.class);
        query.setParameter(1, libName);
        
        List<Library> libraries = query.getResultList();
        
        assertEquals(1, libraries.size());
        
        Library library = libraries.get(0);
        List<Book> books = library.getBooks();
        books.clear();
        
        library.setBooks(books);
        
        em.remove(library);
        em.flush();
        
        assertEquals(0, query.getResultList().size());
    }
}