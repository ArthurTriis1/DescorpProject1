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
public class LibraryTest extends AbstractBasicTest{
    @Test
    public void persistirLibrarySemBook() {
        Library library = new Library();
        library.setName("Sebo bom aconchego");
        
        em.persist(library);
        em.flush();
        assertNotNull(library.getId());
    }
    
    @Test
    public void persistirLibraryComBook() {
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
    public void consultarLibrary() {
        Library library = em.find(Library.class, 2L);
        assertEquals("Livraria Saraiva", library.getName());
        Book book = library.getBooks().get(0);
        Book book2 = library.getBooks().get(1);
        assertNotNull(book);
        assertEquals("Capitães da areia" , book.getTitle());
        assertEquals("Homens e caranguejos" , book2.getTitle());
    }
}
