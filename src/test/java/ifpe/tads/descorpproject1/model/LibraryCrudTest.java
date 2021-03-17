/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.BrazilianStates;
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
        
        Address address = new Address();
        
        address.setComplement("B1");
        address.setNumber(728);
        address.setPostalCode("41.940-370");
        address.setState(BrazilianStates.AC);
        address.setStreet("Rua Dtr Emilio");
        address.setDistrict("Pena");
       
        library.setAddress(address);
        em.persist(library);
        em.flush();
        assertNotNull(library.getId());
    }
    
    @Test
    public void createLibraryWithBook() {
        Library library = new Library();
        library.setName("Loja do saber");
        
        Address address = new Address();
        
        address.setComplement("B2");
        address.setNumber(888);
        address.setPostalCode("49.044-470");
        address.setState(BrazilianStates.AC);
        address.setStreet("Rua Dtr Germano");
        address.setDistrict("Iburs");
       
        library.setAddress(address);

        Book book = new Book();
        book.setTitle("Thor: O Cerco");
        book.setPublisher("Marvel");
        book.setReleaseYear(2012);
        book.setBrazilianISBN("978-65-667-4895-7");
        library.addBook(book);
        
        em.persist(library);
        em.flush();
        
        assertNotNull(library.getId());
        assertNotNull(library.getBooks().get(0).getId());
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
