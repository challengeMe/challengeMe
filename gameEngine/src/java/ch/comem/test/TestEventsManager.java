/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;


import ch.comem.services.EventsManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Leo
 */
@Stateless
@WebService
public class TestEventsManager implements TestEventsManagerLocal {
    @EJB
    private EventsManagerLocal eventsManager;
    
    
    
    @Override
    public void createEvent(Long playerId, String type, Long applicationId) {
        
        eventsManager.createEvent(playerId, type, applicationId);
        
    }

    @Override
    public void removeEvents(Long eventId) {
        
        eventsManager.removeEvent(eventId);
    }

    @Override
    public void changeEvent(String type, Long eventId) {
        
        eventsManager.changeEvent(type, eventId);
    }
    



}
