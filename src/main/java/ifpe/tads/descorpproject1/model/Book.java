/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.Condition;
import ifpe.tads.descorpproject1.validators.BrazilianISBNValidate;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
            ),
            @NamedQuery(
                    name="Book.PorTitulo",
                    query="SELECT b FROM Book b WHERE b.title = :title"
            )
        }
)
public class Book implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "TITLE", length = 150, nullable = false)
    private String title;
    
    @NotNull
    @Max(2100)
    @Column(name = "RELEASE_YEAR")
    private Integer releaseYear;
    
    @NotNull
    @Column(name = "PUBLISHER", length = 150, nullable = false)
    private String publisher;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CONDITION", length = 150, nullable = false)
    private Condition condition;
    
    @DecimalMin("0.01")
    @Column(name = "PRICE", length = 150, nullable = true)
    private Double price;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_AUTHOR", referencedColumnName = "ID")
    private Author author;
    
    @NotNull
    @NotBlank
    @Pattern(regexp = "0*(?=.{17}$)97(?:8|9)([ -])\\d{1,5}\\1\\d{1,7}\\1\\d{1,6}\\1\\d$", 
             message = "{ifpe.tads.descorpproject1.Book.ISBNError}")
    @BrazilianISBNValidate
    @Column(name = "BR_ISBN", length = 25, nullable = true)
    private String brazilianISBN;

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

    public String getBrazilianISBN() {
        return brazilianISBN;
    }

    public void setBrazilianISBN(String brazilianISBN) {
        this.brazilianISBN = brazilianISBN;
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
