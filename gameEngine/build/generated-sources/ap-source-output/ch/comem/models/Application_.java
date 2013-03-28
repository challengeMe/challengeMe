package ch.comem.models;

import ch.comem.models.LeaderBoard;
import ch.comem.models.Player;
import ch.comem.models.Rule;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:25:04")
@StaticMetamodel(Application.class)
public class Application_ { 

    public static volatile SingularAttribute<Application, Long> id;
    public static volatile SingularAttribute<Application, String> description;
    public static volatile SingularAttribute<Application, String> name;
    public static volatile ListAttribute<Application, Player> players;
    public static volatile SingularAttribute<Application, String> apiSecret;
    public static volatile SingularAttribute<Application, String> apiKey;
    public static volatile ListAttribute<Application, Rule> rules;
    public static volatile SingularAttribute<Application, LeaderBoard> leaderBoard;

}