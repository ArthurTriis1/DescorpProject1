/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
public class SellerCrudTest extends AbstractBasicTest{
    @Test
    public void createSeller() {
        Seller seller = new Seller();
        seller.setName("Seller");
        seller.setBirthDay(new Date());
        seller.setLegalDocument("87172656067");
        seller.setPayment(1600.00);
        seller.addPhone("77777-7777");
        seller.setArea("Quadrinhos");
        seller.setEmail("seller@gmail.com");
        em.persist(seller);
        em.flush();
        assertNotNull(seller.getId());
        assertTrue(seller.getPhones().contains("77777-7777"));
    }
    
    @Test
    public void readSeller() {
       // Seller seller = em.find(Seller.class, 2L);
       
        String jpql = "SELECT s FROM Seller s WHERE s.id = ?1";
        TypedQuery<Seller> query = em.createQuery(jpql, Seller.class);
        query.setParameter(1, 1L);
        
        Seller seller = query.getSingleResult();
       
        assertNotNull(seller);
        assertEquals("435.958.910-74" , seller.getLegalDocument());
    }
    
    @Test
    public void updateSeller(){
        String newName = "newName";
        //Seller seller = em.find(Seller.class, 3L);
        
        String jpql = "SELECT s FROM Seller s WHERE s.id = ?1";
        
        TypedQuery<Seller> query = em.createQuery(jpql, Seller.class);
        query.setParameter(1, 1L);
        
        Seller seller = query.getSingleResult();
        
        seller.setName(newName);
        em.clear();        
        em.merge(seller);
        em.flush();
        Seller updatedSeller = em.find(Seller.class, 1L);
        assertEquals(newName, updatedSeller.getName());
    }
    
//    @Test
//    public void deleteSeller(){
//        Seller seller = em.find(Seller.class, 1L);
//        em.remove(seller);
//        Seller deletedSeller = em.find(Seller.class, 1L);
//        assertNull(deletedSeller);
//    }
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirVendedorInvalido() {
        Seller  seller = new Seller();
        Calendar c       = Calendar.getInstance();
        c.set(2023, Calendar.FEBRUARY, 10);
        
        try {
            seller.setName("");
            seller.setBirthDay(c.getTime());
            seller.setLegalDocument("sadas");
            seller.setPayment(30.0);
            seller.setEmail("emailinvalido");
            seller.setPhones(null);
            seller.setArea("");
            em.persist(seller);
            em.flush();
            assertNotNull(seller.getId());
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            
            
            for (ConstraintViolation violation: constraintViolations) {
                System.out.println(violation.getMessage());
                assertThat(violation.getMessage(),
                    
                    CoreMatchers.anyOf(
                        startsWith("O nome do usuario deve ser valido"),
                        startsWith("O cpf informado está em um formato invalido"),
                        startsWith("Datas de nascimento devem ser apenas datas passadas"),
                        startsWith("O Valor minimo de um salario é 1000.00"),
                        startsWith("E-mail invalido"),
                        startsWith("Informe no minimo um telefone do usuario"),
                        startsWith("A Area de atuação no vendedor não deve estar vazia")
                    ));
            }
            
            assertEquals(7, constraintViolations.size());
            assertNull(seller.getId());
            throw ex;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarVendedorInvalido() {
        String jpql = "SELECT s FROM Seller s WHERE s.id = ?1";
        TypedQuery<Seller> query = em.createQuery(jpql, Seller.class);
        query.setParameter(1, 1);
        Seller seller = query.getSingleResult();
        seller.setName("");
        seller.setArea("");
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation violation: constraintViolations) {
                System.out.println(violation.getMessage());
                assertThat(violation.getMessage(),
                    CoreMatchers.anyOf(
                        startsWith("O nome do usuario deve ser valido"),
                        startsWith("A Area de atuação no vendedor não deve estar vazia")
                    ));
            }

            assertEquals(2, ex.getConstraintViolations().size());
            throw ex;
        }
    }

}
