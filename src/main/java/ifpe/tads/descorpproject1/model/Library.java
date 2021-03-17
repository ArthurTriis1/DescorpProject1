    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author arthu
 */
@Entity
@Table(name = "TB_LIBRARY")
@NamedQueries(
        {
            @NamedQuery(
                    name="Library.PorLivro",
                    query="SELECT l FROM Library l JOIN l.books book WHERE book.id = :bookId"
            ),
            @NamedQuery(
                    name="Library.PorTitulo",
                    query="SELECT l FROM Library l JOIN l.books book WHERE book.title = :bookTitle ORDER BY l.id ASC"
            )
        }
)
public class Library implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "O nome deve ser valido")
    @NotBlank(message = "O nome deve ser valido")
    @Column(name = "NAME", length = 150, nullable = false)
    private String name;
    
    @NotNull(message = "O endereço deve ser valido")
    @Valid
    @Embedded
    private Address address;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TB_LIBRARY_BOOK", joinColumns = {
        @JoinColumn(name = "ID_LIBRARY")},
            inverseJoinColumns = {
                @JoinColumn(name = "ID_BOOK")})
    private List<Book> books;
    
    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seller> sellers;
    
    @OneToOne(mappedBy = "library", 
              cascade = CascadeType.PERSIST, 
              fetch = FetchType.LAZY,
              optional = true, 
              orphanRemoval = true)
    private Manager manager;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    public void addBook(Book book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }

        this.books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }
    
    public void addSeller(Seller seller) {
        if (this.sellers == null) {
            this.sellers = new ArrayList<>();
        }

        this.sellers.add(seller);
    }

    public boolean removeSeller(Seller seller) {
        return sellers.remove(seller);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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
        final Library other = (Library) obj;
        return Objects.equals(this.id, other.id);
    }
}
