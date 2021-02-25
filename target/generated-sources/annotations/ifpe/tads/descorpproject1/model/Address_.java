package ifpe.tads.descorpproject1.model;

import ifpe.tads.descorpproject1.enums.BrazilianStates;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.8.v20201217-rNA", date="2021-02-25T18:27:07")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, Integer> number;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, String> district;
    public static volatile SingularAttribute<Address, String> postalCode;
    public static volatile SingularAttribute<Address, BrazilianStates> state;
    public static volatile SingularAttribute<Address, String> complement;

}