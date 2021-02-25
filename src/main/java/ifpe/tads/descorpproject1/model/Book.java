/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.Condition;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author arthu
 */
@Entity
@Table(name = "TB_BOOK")
@NamedQueries(
        {
            @NamedQuery(
                    name="Book.PorId",
                    query="SELECT b FROM Book b WHERE b.id = :bookId"
            )
        }
)
public class Book implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "TITLE", length = 150, nullable = false)
    private String title;
    
    @Column(name = "RELEASE_YEAR")
    private Integer releaseYear;
    
    @Column(name = "PUBLISHER", length = 150, nullable = false)
    private String publisher;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "CONDITION", length = 150, nullable = false)
    private Condition condition;
    
    @Column(name = "PRICE", length = 150, nullable = true)
    private Double price;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_AUTHOR", referencedColumnName = "ID")
    private Author author;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    
    public Double getPrice() {
    return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Book() {
        this.condition = Condition.NEW;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        return Objects.equals(this.id, other.id);
    }
}
