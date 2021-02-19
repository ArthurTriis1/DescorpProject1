/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author arthu
 */
@Entity
@Table(name= "TB_USER")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        name= "DISC_USER", 
        discriminatorType = DiscriminatorType.STRING, 
        length = 3)
public abstract class UserAbstract implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Column(name = "LEGAL_DOCUMENT", nullable = false)
    private String legalDocument;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDay;
    
    @Column(name = "PAYMENT")
    private Double payment;
    
    @ElementCollection
    @CollectionTable(name = "TB_PHONE",
            joinColumns = @JoinColumn(name = "ID_USER", nullable = false))
    @Column(name = "PHONE", nullable = true, length = 20)
    private Collection<String> phones;
    
     public Collection<String> getPhones() {
        return phones;
    }

    public void addPhone(String telefone) {
        if (phones == null) {
            phones = new HashSet<>();
        }
        phones.add(telefone);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalDocument() {
        return legalDocument;
    }

    public void setLegalDocument(String legalDocument) {
        this.legalDocument = legalDocument;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserAbstract)) {
            return false;
        }
        UserAbstract other = (UserAbstract) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
    
}
