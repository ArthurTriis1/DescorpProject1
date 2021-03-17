/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.util.Calendar;
import java.util.Date;
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
public class ManagerCrudTest extends AbstractBasicTest{
    @Test
    public void createManager() {

        Calendar c = Calendar.getInstance();
        c.set(2019, Calendar.FEBRUARY, 10);

        Manager manager = new Manager();
        manager.setName("Manager");
        manager.setBirthDay(c.getTime());
        manager.setLegalDocument("354.126.320-25");
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
    
    
    @Test(expected = ConstraintViolationException.class)
    public void persistirAutorInvalido() {
        Manager  manager = new Manager();
        Calendar c = Calendar.getInstance();
        c.set(2023, Calendar.FEBRUARY, 10);
        
        try {
            manager.setName("");
            manager.setBirthDay(c.getTime());
            manager.setLegalDocument("sadas");
            manager.setPayment(30.0);
            manager.setEmail("emailinvalido");
            manager.setPhones(null);
            em.persist(manager);
            em.flush();
            assertNotNull(manager.getId());
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
                        startsWith("Informe no minimo um telefone do usuario")
                        
                    ));
            }
            
            assertEquals(6, constraintViolations.size());
            assertNull(manager.getId());
            throw ex;
        }
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarGerenteInvalido() {
        String jpql = "SELECT m FROM Manager m WHERE m.id = ?1";
    
        TypedQuery<Manager> query = em.createQuery(jpql, Manager.class);
        query.setParameter(1, 3L);
    
        Manager updatedmanager = query.getSingleResult();
    
        updatedmanager.setName("");
        
        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();
            assertEquals("O nome do usuario deve ser valido", violation.getMessage());
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
