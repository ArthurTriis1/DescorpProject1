package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.model.Address;
import ifpe.tads.descorpproject1.model.Book;
import ifpe.tads.descorpproject1.model.Manager;
import ifpe.tads.descorpproject1.model.Seller;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-02-25T18:27:07")
@StaticMetamodel(Library.class)
public class Library_ { 

    public static volatile SingularAttribute<Library, Address> address;
    public static volatile ListAttribute<Library, Book> books;
    public static volatile SingularAttribute<Library, Manager> manager;
    public static volatile SingularAttribute<Library, String> name;
    public static volatile SingularAttribute<Library, Long> id;
    public static volatile ListAttribute<Library, Seller> sellers;

}