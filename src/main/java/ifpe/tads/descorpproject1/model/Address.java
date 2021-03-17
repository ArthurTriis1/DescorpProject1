/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.constants.Constants;
import ifpe.tads.descorpproject1.enums.BrazilianStates;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author arthu
 */
@Embeddable
public class Address implements Serializable {
    
    @NotNull
    @NotBlank(message = Constants.Erros.Address.STREET)
    @Size(max = 150)
    @Column(name = "ADD_STREET")
    private String street;
    
    @NotNull
    @NotBlank(message = Constants.Erros.Address.DISTRICT)
    @Size(max = 150)
    @Column(name = "ADD_DISTRICT")
    private String district;
    
    @NotNull
    @Min(value = 1, message = Constants.Erros.Address.NUMBER)
    @Max(value = 9999, message = Constants.Erros.Address.NUMBER)
    @Column(name = "ADD_NUMBER")
    private Integer number;
    
    @Size(max = 30, message = Constants.Erros.Address.COMPLEMENT )
    @Column(name = "ADD_COMPLEMENT")
    private String complement;
    
    @NotNull
    @Pattern(regexp = "[0-90]{2}.[0-9]{3}-[0-9]{3}", 
             message = Constants.Erros.Address.POSTAL_CODE )
    @Column(name = "ADD_POSTAL_CODE")
    private String postalCode;
    
    @NotNull(message = Constants.Erros.Address.STATE )
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
