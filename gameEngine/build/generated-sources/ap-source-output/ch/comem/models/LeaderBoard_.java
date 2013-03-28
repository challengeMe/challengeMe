package ch.comem.models;

import ch.comem.models.Application;
import ch.comem.models.Player;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:25:04")
@StaticMetamodel(LeaderBoard.class)
public class LeaderBoard_ { 

    public static volatile SingularAttribute<LeaderBoard, Long> id;
    public static volatile SingularAttribute<LeaderBoard, Application> application;
    public static volatile SingularAttribute<LeaderBoard, String> description;
    public static volatile SingularAttribute<LeaderBoard, String> name;
    public static volatile ListAttribute<LeaderBoard, Player> players;

}