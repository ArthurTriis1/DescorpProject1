/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.BrazilianStates;
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
public class AddressCrudTest extends AbstractBasicTest{
    @Test
    public void createAddress() {
        Library library = new Library();
        library.setName("Sebo com endereço");
        
        String street = "rua";
    
        String district = "bairro";
    
        Integer number = 1;
    
        String complement = "complemento";
    
        String postalCode = "13.606-641";
    
        BrazilianStates state = BrazilianStates.PE;
        
        
        Address address = new Address();
        
        address.setComplement(complement);
        address.getDistrict();
        address.setNumber(number);
        address.setPostalCode(postalCode);
        address.setState(state);
        address.setStreet(street);
        address.setDistrict(district);
        
        library.setAddress(address);

        em.persist(library);
        em.flush();
        assertNotNull(library.getId());
        assertEquals(library.getAddress().getPostalCode(), postalCode);
    }
    
    @Test
    public void readAddress() {
        //Library library = em.find(Library.class, 2L);
        
        String jpql = "SELECT l FROM Library l WHERE l.id = ?1";
        TypedQuery<Library> query = em.createQuery(jpql, Library.class);
        query.setParameter(1, 2L);
        
        Library library = query.getSingleResult();
        
        assertNotNull(library);
        assertEquals("50.761-222" , library.getAddress().getPostalCode());

    }
    
    @Test
    public void updateAddress() {
        String newComplement = "new complement";
        
        //Library library = em.find(Library.class, 2L);
        
        String jpql = "SELECT l FROM Library l WHERE l.id = ?1";
        
        TypedQuery<Library> query = em.createQuery(jpql, Library.class);
        query.setParameter(1, 3L);
        
        Library library = query.getSingleResult();
        
        library.getAddress().setComplement(newComplement);
        
        em.clear();        
        em.merge(library);
        em.flush();
        
        assertEquals(newComplement , library.getAddress().getComplement());

    }
    
    @Test(expected = ConstraintViolationException.class)
    public void deleteAddress() {
        Library library = em.find(Library.class, 1L);
        library.setAddress(null);
        try{
            em.flush();
        }catch(ConstraintViolationException ex){
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            assertEquals(constraintViolations.size(), 1);
            constraintViolations.size();
            throw ex;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirAutorInvalido() {
        Library library = new Library();
        library.setName("Sebo com endereço");
    
        String street = "";
    
        String district = "";
    
        Integer number = 10000;
    
        String complement = "Esse é um complemento com muito mais que trinta caracteres, escrito " +
            "para dar erro";
    
        String postalCode = "13606641";
    
        BrazilianStates state = null;
    
    
        Address address = new Address();
        try {
            address.setComplement(complement);
            address.setNumber(number);
            address.setPostalCode(postalCode);
            address.setState(state);
            address.setStreet(street);
            address.setDistrict(district);
    
            library.setAddress(address);
    
            em.persist(library);
            em.flush();
            assertNotNull(library.getId());
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            
            for (ConstraintViolation violation: constraintViolations) {
                assertThat(violation.getMessage(),
                    CoreMatchers.anyOf(
                        startsWith("O nome da rua não deve ser vazio"),
                        startsWith("O nome do bairro não deve ser vazio"),
                        startsWith("O numero da casa deve ser entre 1 e 9999"),
                        startsWith("O complemento deve ter no máximo 30 caracteres"),
                        startsWith("Número de cep invalido, exemplo: XX.XXX-XXX"),
                        startsWith("A sigla de estado deve ser válida")
                    ));
            }
            
            assertEquals(6, constraintViolations.size());
            assertNull(library.getId());
            throw ex;
        }
    }
    
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarEnderecoInvalido() {
        String newStreet = "";
    
        String jpql = "SELECT l FROM Library l WHERE l.id = ?1";
    
        TypedQuery<Library> query = em.createQuery(jpql, Library.class);
        query.setParameter(1, 3L);
    
        Library library = query.getSingleResult();
    
        library.getAddress().setStreet(newStreet);
        
        try {
            em.clear();
            em.merge(library);
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("O nome da rua não deve ser vazio", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
       
}
