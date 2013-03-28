/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bastieneichenberger
 */
@Entity
@XmlRootElement
public class Mission implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titre;
    
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMission;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    
    private int nbPoints;
    
    @Enumerated(EnumType.STRING)
    private Statut statut;
    
    private String categorie;
    
    // associations
    @OneToOne(mappedBy = "missionAppartientMedia", orphanRemoval = true, cascade = {CascadeType.ALL})
    private Media media;
    
    @ManyToOne
    private Membre membreEffectueMission;
    
    @ManyToOne
    private Membre membreValideMission;

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
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

    public Date getDateMission() {
        return dateMission;
    }

    public void setDateMission(Date dateMission) {
        this.dateMission = dateMission;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mission)) {
            return false;
        }
        Mission other = (Mission) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.models.Mission[ id=" + id + " ]";
    }
    
}
