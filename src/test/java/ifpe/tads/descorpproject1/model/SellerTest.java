/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arthu
 */
public class SellerTest extends AbstractBasicTest{
    @Test
    public void persistirSeller() {
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
    public void consultarSeller() {
        Seller seller = em.find(Seller.class, 1L);
        assertNotNull(seller);
        assertEquals("7789906657" , seller.getLegalDocument());
    }
}
