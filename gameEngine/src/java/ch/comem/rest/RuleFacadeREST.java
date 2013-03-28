/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest;

import ch.comem.models.Rule;
import ch.comem.services.RulesManagerLocal;
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
@Path("rule")
public class RuleFacadeREST {
    
    @EJB
    private RulesManagerLocal rulesManager;
    
    
    /**
     * méthode qui permet de créer une nouvelle règle
     * @param entity, un objet de type Rule
     */
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Rule entity) {
        if(entity.getBadge() == null){
            rulesManager.addRule(entity.getOnEventType(), entity.getApplication().getId(), entity.getNumberOfPoints());
        }
        else {
            rulesManager.addRule(entity.getOnEventType(), entity.getApplication().getId(), entity.getNumberOfPoints(), entity.getBadge().getId());
        }
    }
}
