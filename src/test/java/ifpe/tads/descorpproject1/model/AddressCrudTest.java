/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.BrazilianStates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

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
    
        String postalCode = "cep";
    
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
        assertEquals(library.getAddress().getComplement(), complement);
    }
    
    @Test
    public void readAddress() {
        Library library = em.find(Library.class, 2L);
        assertNotNull(library);
        assertEquals("50761222" , library.getAddress().getPostalCode());

    }
    
    @Test
    public void updateAddress() {
        String newComplement = "new complement";
        
        Library library = em.find(Library.class, 2L);
        library.getAddress().setComplement(newComplement);
        
        em.clear();        
        em.merge(library);
        em.flush();
        
        assertEquals(newComplement , library.getAddress().getComplement());

    }
    
    @Test
    public void deleteAddress() {
        Library library = em.find(Library.class, 1L);
        library.setAddress(null);
        Address deletedAddressOflibrary = em.find(Library.class, 1L).getAddress();
        assertNull(deletedAddressOflibrary);
    }
       
}
