/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bastieneichenberger
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@XmlRootElement
public abstract class Media implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titre;
    
    private String url;
    
    
    @OneToOne
    private Mission missionAppartientMedia;

    public Mission getMissionAppartientMedia() {
        return missionAppartientMedia;
    }

    public void setMissionAppartientMedia(Mission missionAppartientMedia) {
        this.missionAppartientMedia = missionAppartientMedia;
    }
    
    
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if (!(object instanceof Media)) {
            return false;
        }
        Media other = (Media) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.models.Media[ id=" + id + " ]";
    }
    
}
