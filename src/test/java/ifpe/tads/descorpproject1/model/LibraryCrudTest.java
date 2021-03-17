/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.BrazilianStates;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.startsWith;
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
    
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirBibltecaInvalida() {
        Library library = new Library();
        try {
            library.setName("");
    
            Address address = new Address();
            
            address.setNumber(728);
            address.setPostalCode("41940370");
            address.setState(BrazilianStates.AC);
            address.setStreet("Rua Dtr Emilio");
            address.setDistrict("Pena");
    
            library.setAddress(address);
            em.persist(library);
            em.flush();
            assertNotNull(library.getId());
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            
            
            for (ConstraintViolation violation: constraintViolations) {
                assertThat(violation.getMessage(),
                    CoreMatchers.anyOf(
                        startsWith("O nome deve ser valido"),
                        startsWith("Número de cep invalido, exemplo: XX.XXX-XXX")
                    ));
            }
            
            assertEquals(2, constraintViolations.size());
            assertNull(library.getId());
            throw ex;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarBibliotecaInvalida() {
        String jpql = "SELECT l FROM Library l WHERE l.id = :libId";
        TypedQuery<Library> query = em.createQuery(jpql, Library.class);
        query.setParameter("libId", 3L);
    
        Library library = query.getSingleResult();
    
        library.setName("");
    
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("O nome deve ser valido", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
