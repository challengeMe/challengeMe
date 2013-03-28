/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;


import ch.comem.daoExceptions.RESTException;
import ch.comem.models.DTO.MembreDTO;
import ch.comem.models.Membre;
import ch.comem.rest.client.EventClient;
import ch.comem.rest.client.PlayerClient;
import ch.comem.services.MembreManagerLocal;
import com.sun.jersey.api.client.ClientResponse;
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
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author bastieneichenberger
 * Controller pour les membres
 * Les erreurs sont gérées par la class DAOExceptionMapper
 * si le code est différent de 200, il y a eu un problème
 */
@Stateless
@Path("membres")
public class MembreFacadeREST {
    
    @EJB
    private MembreManagerLocal membreManager;
    
    /**
     * méthode qui permet de récupérer un Membre avec son ID
     * @param id
     * @return un objet Membre
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public MembreDTO getMembre(@PathParam("id") Long id) {
        Membre membre = membreManager.readMembre(id);
        Long playerEngineID = membre.getPlayerId();
        // Creer un post sur l'engine
        PlayerClient plClient = new PlayerClient();
        ClientResponse response = plClient.getPlayer_JSON(ClientResponse.class, playerEngineID.toString());
        JSONObject playerEngine = response.getEntity(JSONObject.class);        
        MembreDTO membreDTO = new MembreDTO(membre, playerEngine);
        return membreDTO;
    }
    
    
    /**
     * méthode qui permet d'ajouter un membre
     * @param membre, membre au format xml ou json
     * @return le membre créé
     */ 
    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Membre addMembre(Membre membre) {
        // creer les clients
        PlayerClient plClient = new PlayerClient();
        EventClient evClient = new EventClient();
        // créeation player
        JSONObject player = new JSONObject();
        JSONObject app = new JSONObject();
        try{
            player.put("lastName", membre.getNom());
            player.put("firstName", membre.getPrenom());
            player.put("email", membre.getEmail());
            app.put("id", "1");
            player.put("application", app);
        }
        catch(JSONException e){
            throw new RESTException("problème lors de la création de l'objet JSON: "+e.getMessage());
        }
        String jsonString = player.toString();
        // Evoie du player et récupération de son id
        ClientResponse responsePlayer = plClient.createPlayer_JSON(ClientResponse.class, jsonString);
        JSONObject jsonPlayerEngine  = responsePlayer.getEntity(JSONObject.class);
        Long playerEngineId = null;
        try {
            playerEngineId = jsonPlayerEngine.getLong("id");

        } catch (JSONException ex) {
            throw new RESTException("problème lors de la création de l'objet JSON: "+ex.getMessage());
        }
        Long id = membreManager.createMembre(membre.getNom(), membre.getPrenom(), membre.getEmail(), playerEngineId);
        Membre membreAjoute = membreManager.readMembre(id);
        //event création
        //EventJson event = new EventJson("nouveauMembre", app, playerEngineId);
        //event création
        JSONObject eventJson = new JSONObject();
        JSONObject eventPlayer = new JSONObject();
        try {
            eventJson.put("type","nouveauMembre");
            eventJson.put("application",app);
            eventPlayer.put("id", playerEngineId);
            eventJson.put("player",eventPlayer);
        } catch (JSONException ex) {
            throw new RESTException("problème lors de la création de l'objet JSON: "+ex.getMessage());
        }
        String event_str = eventJson.toString();
        ClientResponse responseEvent = evClient.createEvent_JSON(ClientResponse.class, event_str); 
        return membreAjoute;
    }
    
    /**
     * méthode qui permet de supprimer un membre
     * @param id
     * @return 200 OK si tout est ok, sinon une erreur 404 NOT_FOUND si le membre existait pas
     */
    @DELETE
    @Path("{id}")
    public Response delMembre(@PathParam("id") Long id){
        membreManager.deleteMembre(id);
        return Response.status(200).entity("le membre a bien été supprimé").build();
    }
    
    
    /**
     * méthode qui permet de mettre à jour un membre
     * @param membre, un objet membre à mettre à jour avec son id
     * @return le membre mis à jour
     */
    @PUT
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Membre updateMembre(Membre membre) {
        Long idMembre = membre.getId();
        membreManager.updateMembre(idMembre, membre.getNom(), membre.getPrenom(), membre.getEmail());
        Membre updateMembre = membreManager.readMembre(idMembre);
        return updateMembre;
    }
   
}
