package ch.comem.models;

import ch.comem.models.Application;
import ch.comem.models.Badge;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-25T18:25:04")
@StaticMetamodel(Rule.class)
public class Rule_ { 

    public static volatile SingularAttribute<Rule, Long> id;
    public static volatile SingularAttribute<Rule, Application> application;
    public static volatile SingularAttribute<Rule, String> onEventType;
    public static volatile SingularAttribute<Rule, Integer> numberOfPoints;
    public static volatile SingularAttribute<Rule, Badge> badge;

}