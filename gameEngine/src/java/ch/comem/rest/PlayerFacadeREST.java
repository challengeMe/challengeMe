/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest;

import ch.comem.models.Player;
import ch.comem.services.PlayersManagerLocal;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Leo
 */
@Stateless
@Path("player")
public class PlayerFacadeREST {
    @EJB
    private PlayersManagerLocal playersManager;
    
    /**
     * méthode qui retourne un Player en fonction de l'id
     * @param id, id du player
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Player getPlayer(@PathParam("id") Long id) {
        Player player = playersManager.readPlayer(id);
        return player;
    }
    
    /**
     * méthode qui permet de créer un player
     * @param player
     * @return un Player
     */
    @POST
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Player createPlayer(Player player) {
        Long idPlayer = playersManager.createPlayer(player.getFirstName(), player.getLastName(), player.getEmail(), player.getApplication().getId());
        Player playerAAjouter = playersManager.readPlayer(idPlayer);
        return playerAAjouter;
    }
    /**
     * méthode qui permet de mettre à jour un player
     * @param entity 
     */
    @PUT
    @Consumes({"application/xml", "application/json"})
    public void edit(Player entity) {
        playersManager.changePlayer(entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getId());         
    }
    
    /**
     * méthode qui permet de supprimer un player
     * @param id 
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        playersManager.deletePlayer(id);        
    }
    
}
