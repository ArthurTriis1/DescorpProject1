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
import javax.validation.constraints.NotNull;

/**
 *
 * @author arthu
 */
@Entity
@Table(name="TB_MANAGER")
@DiscriminatorValue(value="MAN")
@PrimaryKeyJoinColumn(name = "ID_USER", referencedColumnName = "ID")
public class Manager extends UserAbstract implements Serializable{
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = true, orphanRemoval = false)
    @JoinColumn(name = "ID_LIBRARY", referencedColumnName = "ID" , nullable = true)
    private Library library;

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Manager)) {
            return false;
        }
        Manager other = (Manager) object;

        return !((this.getId() == null && other.getId() != null) || 
                (this.getId() != null && !this.getId().equals(other.getId())));
    }
}
