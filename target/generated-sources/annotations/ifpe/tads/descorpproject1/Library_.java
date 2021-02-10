package ifpe.tads.descorpproject1;

import ifpe.tads.descorpproject1.Book;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-02-10T16:13:00")
@StaticMetamodel(Library.class)
public class Library_ { 

    public static volatile SingularAttribute<Library, String> address;
    public static volatile ListAttribute<Library, Book> books;
    public static volatile SingularAttribute<Library, String> name;
    public static volatile SingularAttribute<Library, Long> id;

}