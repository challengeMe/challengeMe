/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Application;
import ch.comem.models.Event;
import ch.comem.models.Player;
import ch.comem.models.Rule;
import java.sql.Timestamp;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Leo
 */
@Stateless
public class EventsManager implements EventsManagerLocal {
    @PersistenceContext(unitName = "challengeMePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    /**
     * méthode qui permet de créer un événement
     * @param playerId
     * @param type
     * @param applicationId
     * @return 
     */
    @Override
    public Long createEvent(Long playerId, String type, Long applicationId) {
        
       Event event = new Event();

       event.setType(type);
       
       java.util.Date date= new java.util.Date();
       Timestamp timestamp = new Timestamp(date.getTime());
       event.setDuree(timestamp);
       
       Application app = em.find(Application.class, applicationId);
       event.setApplication(app);
       
       Player player = em.find(Player.class, playerId);
       event.setPlayer(player);
       
       for(Rule rule : app.getRules()){
           if(rule.getOnEventType().equals(type)){
               
               if(rule.getBadge() != null){
                   player.addBadge(rule.getBadge());
               }
                player.addPoints(rule.getNumberOfPoints());           
           }
       }
       em.persist(event);
       em.flush();
       return event.getId();
    }
    
    /**
     * méthode qui permet de supprimer un événement
     * @param eventId
     * @return l'id de l'événement
     */
    @Override
    public Long removeEvent(Long eventId) {
        
        Event event = em.find(Event.class, eventId);
        
        em.remove(event);
        em.flush();

        
        return event.getId();
    }
    
    /**
     * méthode qui permet de modifier un événement
     * @param type
     * @param eventID
     * @return 
     */
    @Override
    public Long changeEvent(String type, Long eventID) {
        
        Event event = em.find(Event.class, eventID);
        event.setType(type);
        
        em.persist(event);
        em.flush();
        
        return event.getId();
    }



}
