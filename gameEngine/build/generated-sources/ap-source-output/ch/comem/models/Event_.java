package ch.comem.models;

import ch.comem.models.Application;
import ch.comem.models.Player;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:25:04")
@StaticMetamodel(Event.class)
public class Event_ { 

    public static volatile SingularAttribute<Event, Long> id;
    public static volatile SingularAttribute<Event, Application> application;
    public static volatile SingularAttribute<Event, Player> player;
    public static volatile SingularAttribute<Event, Timestamp> duree;
    public static volatile SingularAttribute<Event, String> type;

}