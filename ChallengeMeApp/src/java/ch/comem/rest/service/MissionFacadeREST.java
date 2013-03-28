/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.daoExceptions.RESTException;
import ch.comem.models.DTO.MissionDTO;
import ch.comem.models.Mission;
import ch.comem.models.Statut;
import ch.comem.rest.client.EventClient;
import ch.comem.services.MembreManagerLocal;
import ch.comem.services.MissionManagerLocal;
import com.sun.jersey.api.client.ClientResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
@Path("missions")
public class MissionFacadeREST {
    
    
    @EJB
    private MembreManagerLocal membreManager;
    
    @EJB
    private MissionManagerLocal missionManager;
    
    /**
     * méthode qui permet de retourner une mission
     * @param idMission, l'id du membre
     * @return un objet mission
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Mission getMission(@PathParam("id") Long idMission) {
        Mission mission = missionManager.readMission(idMission);
        return mission;
    }
    
    /**
     * méthode qui permet de créer une mission
     * @param missionDTO un ojetmission DTO
     * @param idMedia l'id du média lié à cette mission
     * @param idMembreValide id du membre qui valide la mission
     * @param idMembreEffetue id du membre qui effectue la mission
     * @return un objet mission
     */
    @POST
    @Consumes({"application/xml", "application/json"}) //mimeType
    @Produces({"application/xml", "application/json"})
    public Mission addMission(MissionDTO mission, @QueryParam("idMembreValide") Long idMembreValide, 
    @QueryParam("idMembreEffectue") Long idMembreEffectue) {
        // caster les dates
        Date dateMissionDate = castDate(mission.getDateMission().toString());
        Date dateFinDate = castDate(mission.getDateFin().toString());
        
        Long id = missionManager.createMission(mission.getTitre(), mission.getDescription(), dateMissionDate, 
                dateFinDate, mission.getNbPoints(), 
                Statut.ENCOURS, mission.getCategorie(), idMembreValide, idMembreEffectue);
        Mission missionAjoute = missionManager.readMission(id);
        return missionAjoute;
    }
    
    @PUT
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    public Mission changeStatut(@PathParam("id") Long idMission, @QueryParam("statut") String newStatut) {
        Mission retourMission = missionManager.readMission(idMission);
        String str = newStatut;
        Statut statut = Statut.valueOf(str);
        // si le statut vaut REUSSIE, on déclenche l'événement ajoutePointPlayer ajoute 1000 points
        if(statut == Statut.REUSSIE){
            JSONObject app = new JSONObject();
            try{
                app.put("id", "1");
            }
            catch(JSONException e){
                throw new RESTException("problème lors de la création de l'objet JSON: "+e.getMessage());
            }
            Long playerEngineId = retourMission.getMembreEffectueMission().getId();
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
            EventClient evClient = new EventClient();
            String event_str = eventJson.toString();
            ClientResponse responseEvent = evClient.createEvent_JSON(ClientResponse.class, event_str); 
        }
        Mission newMission = missionManager.changeStatut(idMission, statut);
        return newMission;
    }
    
    //-------------------------------------------- fonctions pour parser les dates ---------------------
    
    public Date castDate(String date){
        Date dateObject = null;
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        try{
            dateObject = sf.parse(date);
        }
        catch(ParseException e){
            throw new RESTException("la date ne peut pas être parsée");
        }
        return dateObject;
    }

 

    
}
