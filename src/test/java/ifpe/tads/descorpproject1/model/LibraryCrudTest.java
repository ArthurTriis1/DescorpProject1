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
    public void readLibrary() {
        Library library = em.find(Library.class, 1L);
        assertEquals("Casa da cultura", library.getName());
        Book book = library.getBooks().get(0);
        Book book2 = library.getBooks().get(1);
        assertNotNull(book);
        assertEquals("Capitães da areia" , book.getTitle());
        assertEquals("Homens e caranguejos" , book2.getTitle());
    }
    
    @Test
    public void updateLibrary() {
        String newName = "Mascarenhas Livros";
        Library library = em.find(Library.class, 3L);
        library.setName(newName);
        em.clear();        
        em.merge(library);
        em.flush();
        Library updatedlibrary = em.find(Library.class, 3L);
        assertEquals(newName , updatedlibrary.getName());
    }

    @Test
    public void deleteLibrary(){
        Library library = em.find(Library.class, 2L);
        em.remove(library);
        Library library2 = em.find(Library.class, 2L);
        assertNull(library2);
    }
}
