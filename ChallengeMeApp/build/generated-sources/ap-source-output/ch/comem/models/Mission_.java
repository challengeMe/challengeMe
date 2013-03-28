package ch.comem.models;

import ch.comem.models.Media;
import ch.comem.models.Membre;
import ch.comem.models.Statut;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:24:47")
@StaticMetamodel(Mission.class)
public class Mission_ { 

    public static volatile SingularAttribute<Mission, Long> id;
    public static volatile SingularAttribute<Mission, Date> dateMission;
    public static volatile SingularAttribute<Mission, Membre> membreEffectueMission;
    public static volatile SingularAttribute<Mission, Statut> statut;
    public static volatile SingularAttribute<Mission, String> categorie;
    public static volatile SingularAttribute<Mission, String> description;
    public static volatile SingularAttribute<Mission, Date> dateFin;
    public static volatile SingularAttribute<Mission, Integer> nbPoints;
    public static volatile SingularAttribute<Mission, Membre> membreValideMission;
    public static volatile SingularAttribute<Mission, Media> media;
    public static volatile SingularAttribute<Mission, String> titre;

}