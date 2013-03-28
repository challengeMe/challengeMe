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
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leo
 */
@Entity
@XmlRootElement
public class Badge implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String icon;
    @ManyToMany(mappedBy = "badges")
    private List<Player> players = new LinkedList<Player>();
    
    public void addPlayer(Player player){

    if(!getPlayers().contains(player)){
        getPlayers().add(player);
    }

    }

    @XmlTransient
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(object instanceof Badge)) {
            return false;
        }
        Badge other = (Badge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.models.badge[ id=" + id + " ]";
    }
    
}
