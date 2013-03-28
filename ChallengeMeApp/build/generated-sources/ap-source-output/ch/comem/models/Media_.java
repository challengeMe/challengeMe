package ch.comem.models;

import ch.comem.models.Mission;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:24:47")
@StaticMetamodel(Media.class)
public abstract class Media_ { 

    public static volatile SingularAttribute<Media, Long> id;
    public static volatile SingularAttribute<Media, Mission> missionAppartientMedia;
    public static volatile SingularAttribute<Media, String> titre;
    public static volatile SingularAttribute<Media, String> url;

}