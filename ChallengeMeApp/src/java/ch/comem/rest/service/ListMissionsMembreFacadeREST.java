/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.models.Membre;
import ch.comem.models.Mission;
import ch.comem.services.MembreManagerLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
@Path("listmissions")
public class ListMissionsMembreFacadeREST {
    
    
    @EJB
    private MembreManagerLocal membreManager;
    
    /**
    * méthode qui permet de retourner les mission d'un membre
    * @param idMembre, ajouter paramètre dans url getMissionFromMembreId
    * @return une liste de Mission
    */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public List<Mission> getMissionFromMembre(@PathParam("id") Long idMembre) {
        Membre membre = membreManager.readMembre(idMembre);
        List<Mission> listMission = membreManager.getMissionFromMembre(membre.getId());
        return listMission;
    }    
}
