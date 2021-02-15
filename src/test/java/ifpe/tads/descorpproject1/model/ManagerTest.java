/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author arthu
 */
public class ManagerTest extends AbstractBasicTest{
    @Test
    public void persistirManager() {
        Manager manager = new Manager();
        manager.setName("Manager");
        manager.setBirthDay(new Date());
        manager.setLegalDocument("7726678890");
        manager.setPayment(2200.00);
        manager.addPhone("99999-9999");
        em.persist(manager);
        em.flush();
        assertNotNull(manager.getId());
    }
    
    @Test
    public void consultarManager() {
        Manager manager = em.find(Manager.class, 3L);
        assertNotNull(manager);
        System.out.println(manager.getName());
        System.out.println(manager.getLegalDocument());
        assertEquals("887664113" , manager.getLegalDocument());
    }
}
