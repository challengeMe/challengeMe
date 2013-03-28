package ch.comem.models;

import ch.comem.models.Groupe;
import ch.comem.models.Mission;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:24:47")
@StaticMetamodel(Membre.class)
public class Membre_ { 

    public static volatile SingularAttribute<Membre, Long> id;
    public static volatile SingularAttribute<Membre, String> prenom;
    public static volatile ListAttribute<Membre, Mission> listMissionDonne;
    public static volatile SingularAttribute<Membre, String> email;
    public static volatile SingularAttribute<Membre, Long> playerId;
    public static volatile ListAttribute<Membre, Groupe> listGroupeCree;
    public static volatile ListAttribute<Membre, Mission> listMission;
    public static volatile ListAttribute<Membre, Groupe> listGroupe;
    public static volatile SingularAttribute<Membre, String> nom;

}