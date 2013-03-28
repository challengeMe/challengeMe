/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.models.Statut;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface PopulateDBLocal {

    void populateDB();
    
    void createMission(String titre, String description, String dateMission, String dateFin, int nbPoints, String statut, String categorie, Long idMembreValide, Long idMembreEffetue);

    void deleteMembre(Long id);

    void deleteMission(Long id);

    void deleteGroupe(Long id);

    void updateMembre(Long id);

    void updateGroupe(Long id);
    
    void updateMission(Long id);
    
}
