/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service;

import ch.comem.models.Membre;
import ch.comem.models.Mission;
import ch.comem.services.MembreManagerLocal;
import ch.comem.services.MissionManagerLocal;
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
@Path("listmissionsavalider")
public class ListMissionsAValiderFacadeREST {
    @EJB
    private MissionManagerLocal missionManager;
    @EJB
    private MembreManagerLocal membreManager;
    
    
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public List<Mission> getMissionAValider(@PathParam("id") Long idMembreValideMission) {
        Membre membre = membreManager.readMembre(idMembreValideMission);
        List<Mission> listMission = missionManager.getMissionAValider(idMembreValideMission);
        return listMission;
    }
    
}
