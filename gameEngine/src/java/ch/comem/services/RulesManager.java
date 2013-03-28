/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Application;
import ch.comem.models.Badge;
import ch.comem.models.Rule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leo
 */
@Stateless
public class RulesManager implements RulesManagerLocal {
    @PersistenceContext(unitName = "challengeMePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    /**
     * méthode qui permet d'ajouter une règle
     * @param onEventType
     * @param appId
     * @param points
     * @param badgeId
     * @return l'id de la règle ajoutée
     */
    @Override
    public Long addRule(String onEventType, Long appId, int points, Long badgeId) {
        
        Rule rule = new Rule();
        rule.setNumberOfPoints(points);
        rule.setOnEventType(onEventType);
        
        Badge badge = em.find(Badge.class, badgeId);
        rule.setBadge(badge);
        
        Application app = em.find(Application.class, appId);
        app.addRule(rule);
        rule.setApplication(app);
        
        em.persist(rule);
        em.persist(app);

        em.flush();
        
        
        return rule.getId();
    }
    
    /**
     * méthode qui permet d'ajouter une règle
     * @param onEventType
     * @param appId
     * @param points
     * @return l'id de la règle ajoutée
     */
    @Override
    public Long addRule(String onEventType, Long appId, int points) {
        
        Rule rule = new Rule();
        rule.setNumberOfPoints(points);
        rule.setOnEventType(onEventType);
        

        Application app = em.find(Application.class, appId);
        app.addRule(rule);
        rule.setApplication(app);
        
        em.persist(rule);
        em.persist(app);

        em.flush();
        
        
        return rule.getId();
    }
    /**
     * méthode qui permet d'ajouter une règle
     * @param onEventType
     * @param appId
     * @param badgeId
     * @return id de la règle ajoutée
     */
    @Override
    public Long addRule(String onEventType, Long appId, Long badgeId) {
        
        Rule rule = new Rule();
        int points = 0;
        rule.setNumberOfPoints(points);
        rule.setOnEventType(onEventType);
        
        Badge badge = em.find(Badge.class, badgeId);
        rule.setBadge(badge);

        Application app = em.find(Application.class, appId);
        app.addRule(rule);
        rule.setApplication(app);
        
        em.persist(rule);
        em.persist(app);

        em.flush();
        
        
        return rule.getId();
    }

    /**
     * méthode qui permet de supprimer une règle
     * @param ruleId
     * @return l'id de la règle supprimée
     */
    @Override
    public Long removeRule(String ruleId) {
        
        Rule rule = em.find(Rule.class, ruleId);
        
        em.remove(rule);
        
        return rule.getId();
    }

    
}
