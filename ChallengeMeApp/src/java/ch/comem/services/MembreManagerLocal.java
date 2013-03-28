/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Groupe;
import ch.comem.models.Membre;
import ch.comem.models.Mission;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cayan
 */
@Local
public interface MembreManagerLocal {

    Long createMembre(String nom, String prenom, String email, Long idPlayerEngine);
    
    void updateMembre(Long id, String newNom, String newPrenom, String newEmail);

    void deleteMembre(Long id);

    Membre readMembre(Long id);
    
    Long addGroupe(Long grpId, Long membreId);
    
    Long addMission(Long missionId, Long membreId);
    
    List<Groupe> getListGroupeFromMembre(Long idMembre);
    
    List<Mission> getMissionFromMembre(Long idMembre);
    
    
    
    
}
