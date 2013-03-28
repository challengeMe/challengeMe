/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Groupe;
import ch.comem.models.Membre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cayan
 */
@Local
public interface GroupeManagerLocal {

    Long createGroupe(Long idMembre, String nom);
    
    void updateGroupe(Long id, String newNom);

    void deleteGroupe(Long id, Long idMembre);

    Groupe readGroupe(Long id);
    
    List<Membre> getListMembresFromGroup(Long idGroupe, Long idMembre);
    
    Long addMembreToGroupe(Long idGroupe, Long idMembreAdmin, Long idMembreAAjouter);
}
