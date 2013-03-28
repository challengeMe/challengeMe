/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.daoExceptions.RESTException;
import ch.comem.models.Statut;
import ch.comem.services.GroupeManagerLocal;
import ch.comem.services.MembreManagerLocal;
import ch.comem.services.MissionManagerLocal;
import ch.comem.services.PhotoManagerLocal;
import ch.comem.services.VideoManagerLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * class qui permet de peupler la BD
 * @author bastieneichenberger
 */
@WebService
@Stateless
public class PopulateDB implements PopulateDBLocal {
    @EJB
    private VideoManagerLocal videoManager;
    @EJB
    private PhotoManagerLocal photoManager;
    @EJB
    private MissionManagerLocal missionManager;
    @EJB
    private MembreManagerLocal membreManager;
    @EJB
    private GroupeManagerLocal groupeManager;

    @Override
    public void populateDB() {
        
       
        
        Long idBastien = membreManager.createMembre("eichenberger", "bastien", "bastien@gmail.com", new Long(1));
        // ajout d'un groupe faculatif
        Long idDarko = membreManager.createMembre("ursewitch", "darko", "darkMir@gmail.com", new Long(2));
        
        Long idCayan = membreManager.createMembre("frei", "cayan", "cayan@gmail.com", new Long(3));
        // ajout d'un groupe faculatif
        Long idSuperArbre = membreManager.createMembre("superArbre", "arbre", "branche@gmail.com", new Long(4));
        
        
        Long idGrpPorn = groupeManager.createGroupe(idBastien, "colloc des cool");
        Long idGrpClasse = groupeManager.createGroupe(idBastien, "class mm 39");
        
        membreManager.addGroupe(idGrpClasse, idBastien);
        membreManager.addGroupe(idGrpClasse, idDarko);
        membreManager.addGroupe(idGrpClasse, idCayan);
        membreManager.addGroupe(idGrpClasse, idSuperArbre);
        
        Long idPhoto = photoManager.createPhoto("nuages", "http://nuage.jpg", "http://vignetteURL");
        Long idVideo = videoManager.createVideo("viveLavie", "http://video.mov", 12.3);
        
        
        Date adj = new Date();
        Date dateFin = addDays(adj, 1);
        
        Long idManger = missionManager.createMission("manger à la cafèt sans mourrir", "aller manger à la cafèt", adj, dateFin, 12, Statut.ENCOURS, "manger", idBastien, idDarko);
        Long idTest = missionManager.createMission("terminer sons tb", "terminer sans tb sans ragequitter", adj, dateFin, 12, Statut.ENCOURS, "études", idDarko, idBastien);
        
        Long idA_valider = missionManager.createMission("courir un marathon", "partir en vacances", adj, dateFin, 12, Statut.A_VALIDE, "études", idDarko, idBastien);
        
        Long idA_valider2 = missionManager.createMission("coder une app android", "boire une bière", adj, dateFin, 12, Statut.A_VALIDE, "études", idDarko, idBastien);
        
        
    }
    
    @Override
    //01-10-2012 15:56"
    public void createMission(String titre, String description, String dateMission, String dateFin, int nbPoints, String statut, String categorie, Long idMembreValide, Long idMembreEffetue){
        Statut statutEnum = Statut.valueOf(statut);
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date dateMissionDate = castDate(dateMission);
        Date dateFinDate = castDate(dateFin);
        missionManager.createMission(titre, description, dateMissionDate, dateFinDate, nbPoints, statutEnum, categorie, idMembreValide, idMembreEffetue);
    }
    
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
    
    public void deleteMembre(Long id){
        membreManager.deleteMembre(id);
    }
    
    @Override
    public void deleteMission(Long id) {
        missionManager.deleteMission(id);
    }
    
    @Override
    public void deleteGroupe(Long id) {
        //groupeManager.deleteGroupe(id);
    }
    
    @Override
    public void updateMembre(Long id) {
          membreManager.updateMembre(id, "modification", "modif", "modific");
    }
      
    @Override
    public void updateGroupe(Long id) {
        groupeManager.updateGroupe(id, "nouveauTitre");
    } 
    
    @Override
    public void updateMission(Long id) {
        missionManager.updateMission(id, "nvlleMission", "nvlleMission", "nvlleMission");
    }
    
    public Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
     }

}
