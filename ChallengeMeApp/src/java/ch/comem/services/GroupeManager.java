/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.daoExceptions.DaoException;
import ch.comem.models.Groupe;
import ch.comem.models.Membre;
import java.util.Iterator;
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
public class GroupeManager implements GroupeManagerLocal {
    
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;
    
    @EJB
    private MembreManagerLocal membreManager;   
    
    
    /**
     * Méthode qui créer un groupe, on connait quel membre a créé quel groupe
     * @param nom, le nom du groupe
     * @return Long, l'id du Membre qui créer le groupe, le nom du nouveau groupe
     */
    @Override
    public Long createGroupe(Long idMembre,String nom) {
        // read membre
        Membre membreCreateur = membreManager.readMembre(idMembre);
        // ajouter le nom du groupe
        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        groupe.setAdministrateur(membreCreateur);
        em.persist(groupe);
        em.flush();
        // ajouter la relation membre qui createur du groupe
        membreCreateur.addGroupeCree(groupe);
        membreCreateur.addGroupe(groupe);
        groupe.addMembreGroupe(membreCreateur);
        
        em.flush();
        
        return groupe.getId();
    }
    /**
     * Méthode qui update un groupe génère un message GROUPE_NOT_FOUND si le groupe n'existe pas
     * @param id, l'id du groupe à modifier
     * @param newNom, le nom du groupe à modifier
     */
    @Override
    public void updateGroupe(Long id, String newNom) {
        Groupe retourGroupe = em.find(Groupe.class, id);
        if(retourGroupe != null){
            retourGroupe.setNom(newNom);
            em.persist(retourGroupe);
            em.flush();
        }
        else{
            throw new DaoException("le groupe n'existe pas", DaoException.StatutsCode.GROUPE_NOT_FOUND);
        }
    }
    /**
     * Méthode qui delete un groupe génère un message GROUP_NOT_FOUND si le groupe n'existe pas
     * cette méthode vérifie la contrainte d'intégrité: le membre qui supprime un groupe doit en être le créateur
     * @param id, l'id du groupe à supprimer
     */
    @Override
    public void deleteGroupe(Long idGroupe, Long idMembre) {
        Groupe retourGroupe = this.readGroupe(idGroupe);
        Membre retourMembre = membreManager.readMembre(idMembre);
        if(this.estAdministrateur(retourGroupe, retourMembre)){
            // supprimer admin
            retourGroupe.getAdministrateur().delGroupeCree(retourGroupe);
            retourGroupe.setAdministrateur(null);
            // supprimer le groupe dans les membre qui en font partie
            List<Membre> listMembreGrp = retourGroupe.getContientMembre();
            // gestion de la list avec un iterator pour éviter concurrence Modification
            Iterator<Membre> it = listMembreGrp.iterator();
            while(it.hasNext()){
                Membre membre = it.next();
                membre.delGroupe(retourGroupe);
                it.remove();
            }
            em.flush();
            em.remove(retourGroupe);
            em.flush();
        }
        else{
            throw new DaoException("le membre doit être le créateur "
                    + "du groupe pour pouvoir le supprimer", DaoException.StatutsCode.CI_NOT_RESPECTED);
        }
    }
    
    /**
     * méthode qui permet d'ajouter un membre à un groupe
     * @param idGroupe
     * @param idMembreAdmin
     * @param idMembreAAjouter
     * @return l'id du groupe créer
     */
    @Override
    public Long addMembreToGroupe(Long idGroupe, Long idMembreAdmin, Long idMembreAAjouter){
        Groupe retourGroupe = this.readGroupe(idGroupe);
        Membre retourMembreAdmin = membreManager.readMembre(idMembreAdmin);
        Membre retourMembreAAjouter = membreManager.readMembre(idMembreAAjouter);
        if(this.estAdministrateur(retourGroupe, retourMembreAdmin)){
            retourGroupe.addMembreGroupe(retourMembreAAjouter);
            retourMembreAAjouter.addGroupe(retourGroupe);
            em.persist(retourGroupe);
            em.persist(retourMembreAAjouter);
            em.flush();
        }
        else{
            throw new DaoException("le membre doit être le créateur "
                    + "du groupe pour pouvoir ajouter des membres", DaoException.StatutsCode.CI_NOT_RESPECTED);
        }
        return retourGroupe.getId();
    }
    
    
    /**
     * Méthode qui read un groupe génère un message GROUPD_NOT_FOUND si le groupe n'existe pas
     * @param id
     * @return un objet Groupe ou null
     */
    @Override
    public Groupe readGroupe(Long id) {
        Groupe retourGroupe = em.find(Groupe.class, id);
        if(retourGroupe == null){
            throw new DaoException("le groupe n'existe pas", DaoException.StatutsCode.GROUPE_NOT_FOUND);
        }
        return retourGroupe;
    }
    
    /**
     * méthode qui retourne tous les membres d'un groupe sauf le membre qui l'appel 
     * génère une exception DAO si le groupe est vide
     * @param idGroupe
     * @return une liste de membre ou null
     */
    @Override
    public List<Membre> getListMembresFromGroup(Long idGroupe, Long idMembre){
        List<Membre> ret = null;
        Groupe groupe = this.readGroupe(idGroupe);
        Membre membre = membreManager.readMembre(idMembre);
        ret = groupe.getContientMembre();
        // delete le membre qui appel la méthode
        groupe.delMembre(membre);
        if(ret.isEmpty()){
            throw new DaoException("le groupe est vide", DaoException.StatutsCode.GROUPE_NOT_FOUND);
        }
        return ret;
    }
    
    // vérifications contraintes d'intégritées --------------------------------------------------------
    /**
     * méthode qui permet de savoir si un membre est bien le créateur d'un groupe
     * @param idGroupe
     * @param idMembre
     * @return true si le membre a bien créé ce groupe, false sinon
     */
    private Boolean estAdministrateur(Groupe retourGroupe, Membre membreAdmin){
        Boolean estAdmin = false;
        Groupe groupeASupprimer = retourGroupe;
        // le groupe que l'on souhaite supprimer a-t-il été créé par ce membre
        Long idCreateurMembre = groupeASupprimer.getAdministrateur().getId();
        if(idCreateurMembre == membreAdmin.getId()){
            estAdmin = true;
        }
        return estAdmin;
    }
    
    



    
    
}
