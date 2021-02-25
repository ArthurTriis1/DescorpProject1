package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.model.Book;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-02-25T18:27:07")
@StaticMetamodel(Author.class)
public class Author_ { 

    public static volatile ListAttribute<Author, Book> books;
    public static volatile SingularAttribute<Author, String> name;
    public static volatile SingularAttribute<Author, Long> id;

}