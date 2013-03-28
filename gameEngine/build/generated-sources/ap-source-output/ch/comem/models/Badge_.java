package ch.comem.models;

import ch.comem.models.Player;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:25:04")
@StaticMetamodel(Badge.class)
public class Badge_ { 

    public static volatile SingularAttribute<Badge, Long> id;
    public static volatile SingularAttribute<Badge, String> icon;
    public static volatile SingularAttribute<Badge, String> title;
    public static volatile SingularAttribute<Badge, String> description;
    public static volatile ListAttribute<Badge, Player> players;

}