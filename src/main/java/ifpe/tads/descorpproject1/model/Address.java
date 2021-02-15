/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.BrazilianStates;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author arthu
 */
@Embeddable
public class Address implements Serializable {
    
    @Column(name = "ADD_STREET")
    private String street;
    
    @Column(name = "ADD_DISTRICT")
    private String district;
    
    @Column(name = "ADD_NUMBER")
    private Integer number;
    
    @Column(name = "ADD_COMPLEMENT")
    private String complement;
    
    @Column(name = "ADD_POSTAL_CODE")
    private String postalCode;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ADD_STATE")
    private BrazilianStates state;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public BrazilianStates getState() {
        return state;
    }

    public void setState(BrazilianStates state) {
        this.state = state;
    }
    
}
