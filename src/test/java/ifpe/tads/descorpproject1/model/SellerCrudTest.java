/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.util.Date;
import javax.persistence.TypedQuery;
import org.junit.Test;
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
        seller.setLegalDocument("009988776655");
        seller.setPayment(1600.00);
        seller.addPhone("77777-7777");
        seller.setArea("Quadrinhos");
        
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
        assertEquals("7789906657" , seller.getLegalDocument());
    }
    
    public void updateSeller(){
        String newName = "new name";
        //Seller seller = em.find(Seller.class, 3L);
        
        String jpql = "SELECT s FROM Seller s WHERE s.id = ?1";
        
        TypedQuery<Seller> query = em.createQuery(jpql, Seller.class);
        query.setParameter(1, 3L);
        
        Seller seller = query.getSingleResult();
        
        seller.setName(newName);
        em.clear();        
        em.merge(seller);
        em.flush();
        Seller updatedSeller = em.find(Seller.class, 3L);
        assertEquals(newName, updatedSeller.getName());
    }
    
//    @Test
//    public void deleteSeller(){
//        Seller seller = em.find(Seller.class, 1L);
//        em.remove(seller);
//        Seller deletedSeller = em.find(Seller.class, 1L);
//        assertNull(deletedSeller);
//    }
}
