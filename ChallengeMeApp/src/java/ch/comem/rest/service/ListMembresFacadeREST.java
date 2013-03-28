/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.models.Groupe;
import ch.comem.models.Membre;
import ch.comem.services.GroupeManagerLocal;
import ch.comem.services.MembreManagerLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
@Path("listmembres")
public class ListMembresFacadeREST {
    
    @EJB
    private MembreManagerLocal membreManager;
    
    @EJB
    private GroupeManagerLocal groupeManager;
    
    
    
    /**
    * méthode qui permet de récupérer la list des membre appartenant à un groupe sans le membre qui l'appel
    * @param idGroupe
    * @param idMembre
    * @return une List de membre
    */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public List<Membre> getMembresFromGroup(@PathParam("id") Long idGroupe,
    @QueryParam("idMembre") Long idMembre) {
        Groupe groupe = groupeManager.readGroupe(idGroupe);
        Membre retourMembre = membreManager.readMembre(idMembre);
        List<Membre> listMembre = groupeManager.getListMembresFromGroup(idGroupe, idMembre);
        return listMembre;
    }
    
}
