package ifpe.tads.descorpproject1.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-02-25T14:30:13")
@StaticMetamodel(UserAbstract.class)
public abstract class UserAbstract_ { 

    public static volatile SingularAttribute<UserAbstract, Date> birthDay;
    public static volatile SingularAttribute<UserAbstract, String> name;
    public static volatile CollectionAttribute<UserAbstract, String> phones;
    public static volatile SingularAttribute<UserAbstract, Double> payment;
    public static volatile SingularAttribute<UserAbstract, Long> id;
    public static volatile SingularAttribute<UserAbstract, String> legalDocument;

}