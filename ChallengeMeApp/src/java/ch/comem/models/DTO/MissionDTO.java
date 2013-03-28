/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models.DTO;

import ch.comem.models.Media;
import ch.comem.models.Membre;
import ch.comem.models.Statut;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bastieneichenberger
 */
@XmlRootElement
public class MissionDTO implements Serializable {

    private Long id;
    
    private String titre;
    
    private String description;
    
    private String dateMission;
    
    private String dateFin;
    
    private int nbPoints;
    
    private String statut;
    
    private String categorie;
    
    // associations
    private Media media;
    
    private Membre membreEffectueMission;
    
    private Membre membreValideMission;

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Membre getMembreValideMission() {
        return membreValideMission;
    }

    public void setMembreValideMission(Membre membreValideMission) {
        this.membreValideMission = membreValideMission;
    }
    
    public Membre getMembreEffectueMission() {
        return membreEffectueMission;
    }

    public void setMembreEffectueMission(Membre membreEffectueMission) {
        this.membreEffectueMission = membreEffectueMission;
    }
    

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateMission() {
        return dateMission;
    }

    public void setDateMission(String dateMission) {
        this.dateMission = dateMission;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ch.comem.models.Mission[ id=" + id + " ]";
    }
    
}
