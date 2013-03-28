/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest;

import ch.comem.models.Event;
import ch.comem.services.EventsManagerLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Leo
 */
@Stateless
@Path("event")
public class EventFacadeREST {
    
    @EJB
    private EventsManagerLocal eventsManager;
    /**
     * méthode qui péermet d'ajouter un événement
     * @param entity, un object de type Event
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Event entity) {        
        eventsManager.createEvent(entity.getPlayer().getId(), entity.getType(), entity.getApplication().getId());
    }
    
}
