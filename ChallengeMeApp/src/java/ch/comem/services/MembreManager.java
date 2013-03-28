/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.daoExceptions.DaoException;
import ch.comem.models.Groupe;
import ch.comem.models.Membre;
import ch.comem.models.Mission;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cayan
 */
@Stateless
public class MembreManager implements MembreManagerLocal {
    @EJB
    private GroupeManagerLocal groupeManager;
    
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;
    
    /**
     * Méthode qui créer un membre
     * @param nom
     * @param prenom
     * @param email
     * @param point
     * @return Long, l'id du groupe
     */
    @Override
    public Long createMembre(String nom, String prenom, String email, Long idPlayerEngine) {
        Membre membre = new Membre();
        membre.setNom(nom);
        membre.setPrenom(prenom);
        membre.setEmail(email);
        membre.setPlayerId(idPlayerEngine);
        em.persist(membre);
        em.flush();
        return membre.getId();
    }
    /**
     * méthode qui permet d'ajouter un groupe
     * @param grpId
     * @param membreId
     * @return l'id du groupe
     */
    @Override
    public Long addGroupe(Long grpId, Long membreId){
        Groupe retourGroupe = em.find(Groupe.class, grpId);
        Membre retourMembre = em.find(Membre.class, membreId);
        if(retourGroupe != null && retourMembre != null){
            retourGroupe.addMembreGroupe(retourMembre);
            retourMembre.addGroupe(retourGroupe);
            em.persist(retourGroupe);
            em.persist(retourMembre);
            em.flush();
        }
        else if (retourGroupe == null){
            throw new DaoException("le groupe n'existe pas", DaoException.StatutsCode.GROUPE_NOT_FOUND);
        }
        else if (retourMembre == null){
            throw new DaoException("le membre n'existe pas", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
        return retourMembre.getId();
    }
    
    
    
    /**
     * Méthode qui update un membre génère un message MEMBRE_NOT_FOUND si le membre n'existe pas
     * @param id
     * @param newNom
     * @param newPrenom
     * @param newEmail
     * @param newPoints 
     */
    @Override
    public void updateMembre(Long id, String newNom, String newPrenom, String newEmail) {
        Membre retourMembre = em.find(Membre.class, id);
        if(retourMembre != null){
            retourMembre.setNom(newNom);
            retourMembre.setPrenom(newPrenom);
            retourMembre.setEmail(newEmail);
            em.persist(retourMembre);
            em.flush();
        }
        else{
            throw new DaoException("le membre n'existe pas", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
    }
    /**
     * Méthode qui delete un membre génère un message MEMBRE_NOT_FOUND si le membre n'existe pas
     * @param id 
     */
    @Override
    public void deleteMembre(Long id) {
        Membre retourMembre = em.find(Membre.class, id);
        if(retourMembre != null){
            em.remove(retourMembre);
        }
        else{
            throw new DaoException("le membre n'existe pas", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
    }
    /**
     * Méthode qui read un membre génère un message [OBJECTVALIDE ou OBJETINEXISTANT]
     * @param id
     * @return un objet Membre ou null
     */
    @Override
    public Membre readMembre(Long id) {
        Membre retourMembre = em.find(Membre.class, id);
        if(retourMembre == null){
            throw new DaoException("le membre n'existe pas", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
        return retourMembre;
    }
    
    
    /**
     * méthode qui ajoute une mission à un membre
     * @param missionId
     * @param membreId
     * @return l'id de la mission
     */
    @Override
    public Long addMission(Long missionId, Long membreId) {
        Membre retourMembre = em.find(Membre.class, membreId);
        Mission retourMission = em.find(Mission.class, missionId);
        if(retourMembre != null && retourMission != null){
            retourMembre.addMission(retourMission);
            retourMission.setMembreEffectueMission(retourMembre);
            em.persist(retourMembre);
            em.persist(retourMission);
            em.flush();
        }
        else if (retourMembre == null){
            throw new DaoException("le membre n'existe pas", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
        else if (retourMission == null){
            throw new DaoException("la mission n'existe pas", DaoException.StatutsCode.MISSION_NOT_FOUND);
        }
        return retourMission.getId();
    }
    
    /**
     * méthode qui retourne la list des groupe du membre
     * @param idMembre
     * @return 
     */
    @Override
    public List<Groupe> getListGroupeFromMembre(Long idMembre){
        List<Groupe> ret = null;
        Membre membre = this.readMembre(idMembre);
        ret = membre.getListGroupe();
        if(ret.isEmpty()){
            throw new DaoException("le membre ne fait parti d'aucun groupe", DaoException.StatutsCode.MEMBRE_NOT_FOUND);
        }
        return ret;
    }
    
    /**
     * méthode qui retourne la liste de mission du membre
     * @param idMembre
     * @return 
     */
    @Override
    public List<Mission> getMissionFromMembre(Long idMembre){
        List<Mission> ret = null;
        Membre membre = this.readMembre(idMembre);
        ret = membre.getListMission();
        if(ret.isEmpty()){
            throw new DaoException("le membre n'a pas de mission", DaoException.StatutsCode.MISSION_NOT_FOUND);
        }
        return ret;
    }
    
    


    

    
}
