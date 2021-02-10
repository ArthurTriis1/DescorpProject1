/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpe.tads.descorpproject1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author arthu
 */
@Entity
@Table(name = "TB_BOOK")
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
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ID_AUTHOR", referencedColumnName = "ID")
    private Author author;
    
//    @ManyToMany(mappedBy = "library")
//    private List<Library> libraries;

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

//    public List<Library> getLibraries() {
//        return libraries;
//    }

//    public void setLibraries(List<Library> libraries) {
//        this.libraries = libraries;
//    }
//    
//    public void addLibrary(Library library) {
//        if (this.libraries == null) {
//            this.libraries = new ArrayList<>();
//        }
//
//        this.libraries.add(library);
//    }
//
//    public boolean removeBook(Library library) {
//        return libraries.remove(library);
//    }
}
