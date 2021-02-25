package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.Condition;
import ifpe.tads.descorpproject1.model.Author;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-02-25T18:27:07")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, Condition> condition;
    public static volatile SingularAttribute<Book, Double> price;
    public static volatile SingularAttribute<Book, Author> author;
    public static volatile SingularAttribute<Book, String> publisher;
    public static volatile SingularAttribute<Book, Long> id;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, Integer> releaseYear;

}