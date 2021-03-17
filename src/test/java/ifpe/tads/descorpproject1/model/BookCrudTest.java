/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.Condition;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import java.util.Set;

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
        book.setBrazilianISBN("978-85-883-6678-7");
        
        String jpql = "SELECT a FROM Author a WHERE a.id = ?1";
        TypedQuery<Author> query = em.createQuery(jpql, Author.class);
        query.setParameter(1, 2L);
        
        Author author = query.getSingleResult();
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
        book.setBrazilianISBN("978-85-899-4312-7");
        
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
        book1.setBrazilianISBN("978-85-883-4355-7");

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
        
        TypedQuery<Book> query = em.createNamedQuery("Book.PorId", Book.class);
        query.setParameter("bookId", idLivro);
        
        Book book = query.getSingleResult();
        
        String tituloEditado = book.getTitle() + editado;
        String editoraEditado = book.getPublisher() + editado;
        
        book.setTitle(tituloEditado);
        book.setPublisher(editoraEditado);
        
        em.flush();
        
        book = query.getSingleResult();
        
        assertEquals(tituloEditado, book.getTitle());
        assertEquals(editoraEditado, book.getPublisher());
    }
    
    @Test
    public void countBooksByConditionAndYear() {
        int year = 1960;
        
        String jpql = "SELECT b FROM Book b WHERE b.condition = :condition AND b.releaseYear > :year";
        TypedQuery<Book> query = em.createQuery(jpql, Book.class);
        query.setParameter("condition", Condition.MANIPULATED);
        query.setParameter("year", year);
        
        List<Book> books = query.getResultList();
        
        assertEquals(6, books.size());
    }
    /*
    @Test
    public void removeBook() {        
        String bookTitle = "Dona Flor e seus dois maridos";
        
        TypedQuery<Library> libQuery = em.createNamedQuery("Library.PorTitulo", Library.class);
        libQuery.setParameter("title", bookTitle);
        
        TypedQuery<Book> bookQuery = em.createNamedQuery("Book.PorTitulo", Book.class);        
        bookQuery.setParameter("title", bookTitle);
        
        List<Library> libraries = libQuery.getResultList();
        Book book = bookQuery.getSingleResult();
        
        libraries.forEach(library -> {
            library.removeBook(book);
        });
        
        em.remove(book);
        em.flush();
        
        assertEquals(0, bookQuery.getResultList().size());
    }
    */

    @Test(expected = ConstraintViolationException.class)
    public void persistirBookInvalido() {
        Book book = new Book();
        try {
            book.setTitle("");
            book.setPublisher("");
            book.setReleaseYear(2101);
            book.setBrazilianISBN("978-35-866683-6678-7");
            book.setPrice(0.00);
            book.setCondition(null);

            String jpql = "SELECT a FROM Author a WHERE a.id = ?1";
            TypedQuery<Author> query = em.createQuery(jpql, Author.class);
            query.setParameter(1, 2L);

            Author author = query.getSingleResult();
            assertEquals("João Cabral", author.getName());

            book.setAuthor(author);

            em.persist(book);
            em.flush();
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();


            for (ConstraintViolation violation: constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(
                            startsWith("ISBN invalido. O ISBN deve ser brasileiro e com o prefixo" +
                                " GS1."),
                            startsWith("O nome de um livro não deve ser estar em branco"),
                            startsWith("Não trabalhamos com datas acima do ano 2100"),
                            startsWith("Um livro tem o valor minimo de R$0,01"),
                            startsWith("Um livro deve conter um nome de editora valida"),
                            startsWith("Um livro deve conter um estado valido"),
                            startsWith("ISBN invalido. O ISBN deve ser brasileiro, com o codigo 65 ou 85.")
                        ));
            }

            assertEquals(7, constraintViolations.size());
            assertNull(book.getId());
            throw ex;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarLivroInvalido() {
        Long idLivro = 1L;
    
        TypedQuery<Book> query = em.createNamedQuery("Book.PorId", Book.class);
        query.setParameter("bookId", idLivro);
    
        Book book = query.getSingleResult();
    
        book.setTitle("");
        
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("O nome de um livro não deve ser estar em branco", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
