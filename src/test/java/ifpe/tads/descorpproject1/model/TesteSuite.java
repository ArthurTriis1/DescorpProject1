/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author masc1
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
                        ifpe.tads.descorpproject1.model.BookCrudTest.class,
                        ifpe.tads.descorpproject1.model.AddressCrudTest.class,
                        ifpe.tads.descorpproject1.model.AuthorCrudTest.class,
                        ifpe.tads.descorpproject1.model.LibraryCrudTest.class,
                        ifpe.tads.descorpproject1.model.ManagerCrudTest.class,
                        ifpe.tads.descorpproject1.model.SellerCrudTest.class,
                        ifpe.tads.descorpproject1.model.JpqlTest.class
                    })
public class TesteSuite {
    
}
