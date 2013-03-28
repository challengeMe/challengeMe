/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.models.Groupe;
import ch.comem.services.GroupeManagerLocal;
import ch.comem.services.MembreManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
@Path("groupes")
public class GroupeFacadeREST {
    @EJB
    private MembreManagerLocal membreManager;
    @EJB
    private GroupeManagerLocal groupeManager;
    
    
    
    /**
     * méthode qui permet de récupérer un groupe
     * @param id id du groupe
     * @return un objet Groupe
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Groupe getGroupe(@PathParam("id") Long id) {
        Groupe groupe = groupeManager.readGroupe(id);
        return groupe;
    }
    
    /**
     * méthode qui permet d'ajouter un groupe
     * @param membreCreateur
     * @param groupe
     * @param membre, le membre qui a créé ce groupe
     * @return le groupe ajouté
     */
    @POST
    @Consumes({"application/xml", "application/json"}) //mimeType
    @Produces({"application/xml", "application/json"})
    public Groupe addGroupe(@QueryParam("idMembreAdmin") Long idMembreAdmin, Groupe groupe) {
        Long id = groupeManager.createGroupe(idMembreAdmin, groupe.getNom());
        Groupe groupeAjoute = groupeManager.readGroupe(id);
        return groupeAjoute;
    }
    
    
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"}) //mimeType
    @Produces({"application/xml", "application/json"})
    public Groupe addMembreToGroupe(@PathParam("id") Long idGroupe, @QueryParam("idMembreAdmin") Long idMembreAdmin, 
    @QueryParam("idMembreAAjouter") Long idMembreAAjouter){
        // seul le propriétaire peux ajouter des membre dans un groupe
        groupeManager.addMembreToGroupe(idGroupe, idMembreAdmin, idMembreAAjouter);
        Groupe groupeModifie = groupeManager.readGroupe(idGroupe);
        return groupeModifie;
    }
    /**
     * méthode qui permet de supprimer un groupe si le membre est l'admin de ce groupe
     * @param idGroupe
     * @param idMembreAdmin paramètre 2 à mettre dans l'url /idGroupe?idMembreAdmin=1
     * @return 200 si tout est ok, sinon une erreur DAO
     */
    @DELETE
    @Path("{id}")
    public Response delGroupe(@PathParam("id") Long idGroupe, @QueryParam("idMembreAdmin") Long idMembreAdmin){
        groupeManager.deleteGroupe(idGroupe, idMembreAdmin);
        return Response.status(200).entity("le groupe a bien été supprimé").build();
    }
    
    
}
