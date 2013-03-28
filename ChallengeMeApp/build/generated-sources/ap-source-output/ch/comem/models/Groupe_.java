package ch.comem.models;

import ch.comem.models.Membre;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:24:47")
@StaticMetamodel(Groupe.class)
public class Groupe_ { 

    public static volatile SingularAttribute<Groupe, Long> id;
    public static volatile SingularAttribute<Groupe, Membre> administrateur;
    public static volatile SingularAttribute<Groupe, String> nom;
    public static volatile ListAttribute<Groupe, Membre> contientMembre;

}