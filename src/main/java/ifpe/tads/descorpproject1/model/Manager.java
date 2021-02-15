/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author arthu
 */
@Entity
@Table(name="TB_MANAGER")
@DiscriminatorValue(value="MAN")
@PrimaryKeyJoinColumn(name = "ID_USER", referencedColumnName = "ID")
public class Manager extends UserAbstract implements Serializable{
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "ID_LIBRARY", referencedColumnName = "ID")
    private Library library;

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
    
    
}
