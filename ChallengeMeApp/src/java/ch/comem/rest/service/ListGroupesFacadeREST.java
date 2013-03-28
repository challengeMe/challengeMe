/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.models.Groupe;
import ch.comem.models.Membre;
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
@Path("listgroupes")
public class ListGroupesFacadeREST {
    
    @EJB
    private MembreManagerLocal membreManager;
    
     /**
     * métdhode qui retourne la list des groupe d'un membre
     * @param idMembre, param idMembre
     * @return une liste de Groupe
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public List<Groupe> getGroupesFromMembre(@PathParam("id") Long idMembre) {
        Membre membre = membreManager.readMembre(idMembre);
        List<Groupe> listGroupe = membreManager.getListGroupeFromMembre(membre.getId());
        return listGroupe;
    }
    
}
