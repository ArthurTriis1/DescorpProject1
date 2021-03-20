/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.constants.Constants;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author arthu
 */
@Entity
@Table(name="TB_SELLER")
@DiscriminatorValue(value="SEL")
@PrimaryKeyJoinColumn(name = "ID_USER", referencedColumnName = "ID")
public class Seller extends UserAbstract implements Serializable{
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_LIBRARY", referencedColumnName = "ID")
    private Library library;
    
    @NotBlank(message = "{ifpe.tads.descorpproject1.Seller.area}")
    @Column(name = "AREA")
    private String area;

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Seller)) {
            return false;
        }
        Seller other = (Seller) object;

        return !((this.getId() == null && other.getId() != null) || 
                (this.getId() != null && !this.getId().equals(other.getId())));
    }
    
    
}
