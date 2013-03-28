/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cayan
 */
@Entity
@XmlRootElement
public class Groupe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    //Associations
    @ManyToMany
    private List<Membre> contientMembre = new LinkedList<Membre>();
    
    @ManyToOne
    private Membre administrateur;

    public List<Membre> getContientMembre() {
        return contientMembre;
    }

    public void setContientMembre(List<Membre> contientMembre) {
        this.contientMembre = contientMembre;
    }

    public Membre getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Membre administrateur) {
        this.administrateur = administrateur;
    }
    
    
    public void addMembreGroupe(Membre membre){
        if(!this.contientMembre.contains(membre)){
            this.contientMembre.add(membre);
        }
    }
    
    public void delMembre(Membre membre){
        if(this.contientMembre.contains(membre)){
            this.contientMembre.remove(membre);
        }
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
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.models.Groupe[ id=" + id + " ]";
    }
     public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(Long id) {
        this.id = id;
    }  
    
    
    
    
}
