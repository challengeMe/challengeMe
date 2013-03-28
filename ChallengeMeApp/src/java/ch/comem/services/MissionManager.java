/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.daoExceptions.DaoException;
import ch.comem.models.Groupe;
import ch.comem.models.Media;
import ch.comem.models.Membre;
import ch.comem.models.Mission;
import ch.comem.models.Statut;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
public class MissionManager implements MissionManagerLocal {
    @EJB
    private MediaManagerLocal mediaManager;
    
    @EJB
    private MembreManagerLocal membreManager;
    
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;
    
    

    /**
     * Méthode qui créer une mission
     * @param titre, le titre de la mission
     * @param description, la description
     * @param dateMission, la date de début au format Date
     * @param duree, la duree de la mission
     * @param nbPoints, le nombre de points
     * @param statut, le statut [ENCOURS, ABANDONNE, TERMINE]
     * @param categorie, la catégorie
     * @param membre, un objet Membre
     * @param groupe, un objet Groupe
     * @return Long, l'id de la mission qui a été créée
     */
    @Override
    public Long createMission(String titre, String description, Date dateMission, Date dateFin, 
    int nbPoints, Statut statut, String categorie, Long idMembreValide, Long idMembreEffetue) {
        Mission mission = new Mission();
        mission.setTitre(titre);
        mission.setDescription(description);
        mission.setDateMission(dateMission);
        mission.setDateFin(dateFin);
        mission.setNbPoints(nbPoints);
        mission.setStatut(statut);
        mission.setCategorie(categorie);
        // relations membre mision effectue
        Membre membreEffectue = membreManager.readMembre(idMembreEffetue);
        mission.setMembreEffectueMission(membreEffectue);
        membreEffectue.addMission(mission);
        // relations membre mission valide
        Membre membreValide = membreManager.readMembre(idMembreValide);
        mission.setMembreValideMission(membreValide);
        membreValide.addListMissionDonne(mission);
        //em.persist(groupe);
        em.persist(membreEffectue);
        em.persist(membreValide);
        em.persist(mission);
        em.flush();
        return mission.getId();
    }
    
    /**
     * Méthode qui update une mission génère un message MISSION_NOT_FOUND si l'objet n'existe pas
     * @param id, l'id de la mission à modifier
     * @param newTitre, le nouveau titre
     * @param newDescription, la nouvelle description
     * @param newCat, la nouvelle catégorie
     */
    @Override
    public void updateMission(Long id, String newTitre, String newDescription, String newCat) {
        Mission retourMission = em.find(Mission.class, id);
        if(retourMission != null){
            retourMission.setTitre(newTitre);
            retourMission.setDescription(newDescription);
            retourMission.setCategorie(newCat);
            em.persist(retourMission);
            em.flush();
        }
        else{
            throw new DaoException("la mission n'existe pas", DaoException.StatutsCode.MISSION_NOT_FOUND);
        }
    }
    /**
     * Méthode qui delete une mission génère un message MISSION_NOT_FOUND si l'objet n'existe pas
     * @param id, l'id de la mission à supprimer
     */
    @Override
    public void deleteMission(Long id) {
        Mission retourMission = em.find(Mission.class, id);
        if(retourMission != null){
            em.remove(retourMission);
        }
        else{
            throw new DaoException("la mission n'existe pas", DaoException.StatutsCode.MISSION_NOT_FOUND);
        }
    }
    /**
     * Méthode qui read une mission génère un message MISSION_NOT_FOUND si l'objet n'existe pas
     * @param id, l'id de la mission à lire
     * @return un objet Mission ou null
     */
    @Override
    public Mission readMission(Long id) {
        Mission retourMission = em.find(Mission.class, id);
        if(retourMission == null){
            throw new DaoException("la mission n'existe pas", DaoException.StatutsCode.MISSION_NOT_FOUND);
        }
        return retourMission;
    }
    
    
    /**
     * méthode qui permet de changer le statut d'une mission
     * @param id
     * @param newStatut
     * @return une mission
     */
    @Override
    public Mission changeStatut(Long id, Statut newStatut){
        Mission retourMission = readMission(id);
        retourMission.setStatut(newStatut);
        return retourMission;
    }
    
    
    /**
     * méthode qui retourne la liste des missions à valider statut = A_VALIDER, id du membre = celui qui doit valider la mission
     * @param idMembreValideMission
     * @return une liste de missions
     */
    @Override
    public List<Mission> getMissionAValider(Long idMembreValideMission){
        List<Mission> ret = null;
        Query query = em.createQuery("SELECT m FROM Mission m WHERE m.statut = "+Statut.class.getName()+".A_VALIDE AND m.membreValideMission.id = "+idMembreValideMission);
        ret = query.getResultList();
        if(ret.isEmpty()){
            throw new DaoException("le membre n'a pas de mission à valider", DaoException.StatutsCode.MISSION_NOT_FOUND);
        }
        return ret;
    }
    
     

}
