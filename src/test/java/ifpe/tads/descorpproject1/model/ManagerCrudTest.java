/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.util.Date;
import javax.persistence.TypedQuery;
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
        manager.setLegalDocument("78042939094");
        manager.setPayment(2200.00);
        manager.addPhone("99999-9999");
        manager.setEmail("manager@gmail.com");
        manager.addPhone("88166789");
        em.persist(manager);
        em.flush();
        assertNotNull(manager.getId());
    }
    
    @Test
    public void readManager() {
        //Manager manager = em.find(Manager.class, 3L);
        
        String jpql = "SELECT m FROM Manager m WHERE m.id = ?1";
        TypedQuery<Manager> query = em.createQuery(jpql, Manager.class);
        query.setParameter(1, 3L);
        
        Manager manager = query.getSingleResult();
        
        assertNotNull(manager); 
        
        assertEquals("Jão", manager.getName());
             
        assertEquals("68693246038" , manager.getLegalDocument());
        
    }
    
        @Test
    public void updateManager(){
        String newName = "Jão";
        
        String jpql = "SELECT m FROM Manager m WHERE m.id = ?1";
        
        TypedQuery<Manager> query = em.createQuery(jpql, Manager.class);
        query.setParameter(1, 3L);
        
        Manager updatedmanager = query.getSingleResult();
        
        //Manager manager = em.find(Manager.class, 3L);
        updatedmanager.setName(newName);
        updatedmanager.setLegalDocument("68693246038");
        em.clear();        
        em.merge(updatedmanager);
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
