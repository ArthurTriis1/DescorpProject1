/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author arthu
 */
public class ManagerCrudTest extends AbstractBasicTest{
    @Test
    public void createManager() {
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
    public void readManager() {
        Manager manager = em.find(Manager.class, 3L);
        assertNotNull(manager);
        assertEquals("887664113" , manager.getLegalDocument());
    }
    
        @Test
    public void updateManager(){
        String newName = "Jão";
        Manager manager = em.find(Manager.class, 3L);
        manager.setName(newName);
        em.clear();        
        em.merge(manager);
        em.flush();
        Manager updatedManager = em.find(Manager.class, 3L);
        assertEquals(newName, updatedManager.getName());
    }
    
    @Test
    public void deleteManager(){
        Manager manager = em.find(Manager.class, 4L);
        em.remove(manager);
        Manager deletedManager = em.find(Manager.class, 4L);
        assertNull(deletedManager);
    }
}
