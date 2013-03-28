package ch.comem.models;

import ch.comem.models.Application;
import ch.comem.models.Badge;
import ch.comem.models.Event;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:25:04")
@StaticMetamodel(Player.class)
public class Player_ { 

    public static volatile SingularAttribute<Player, Long> id;
    public static volatile SingularAttribute<Player, String> lastName;
    public static volatile ListAttribute<Player, Badge> badges;
    public static volatile SingularAttribute<Player, Application> application;
    public static volatile SingularAttribute<Player, String> email;
    public static volatile ListAttribute<Player, Event> events;
    public static volatile SingularAttribute<Player, Integer> numberOfPoints;
    public static volatile SingularAttribute<Player, String> firstName;

}