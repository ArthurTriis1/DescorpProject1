/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author arthu
 */
public abstract class AbstractBasicTest {
    protected static EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction et;
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("DescorpProject1");
        DbUnitUtil.inserirDados();        
    }
    
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }
    
    @Before
    public void setUp() {
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();        
    }
    
    @After
    public void tearDown() {
        if (!et.getRollbackOnly()) {
            et.commit();
        }
        em.close();        
    }
}
