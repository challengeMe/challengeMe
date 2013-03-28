/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leo
 */
@Entity
@XmlRootElement
public class Player implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String email;
    private int numberOfPoints;
    @ManyToMany
    private List<Badge> badges = new LinkedList<Badge>();
    @OneToMany(mappedBy = "player")
    private List<Event> events = new LinkedList<Event>();
    @ManyToOne 
    private Application application;
    
    //@XmlTransient
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
    public void addEvent(Event event){

    if(!getEvents().contains(event)){
        getEvents().add(event);
    }
        
    }


    @XmlTransient
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addBadge(Badge badge){
        
        if(!getBadges().contains(badge)){
            getBadges().add(badge);
        }
        
    }
    
    public void addPoints(int points){
        
        if(this.numberOfPoints >= 0){
            int temp = getNumberOfPoints() + points;
            setNumberOfPoints(temp);
        }
        
    }
    public void removePoints(int points){
        
        if(this.numberOfPoints >= 0 && this.numberOfPoints > points){
            int temp = getNumberOfPoints() - points;
            setNumberOfPoints(temp);
        
        }
        else if(this.numberOfPoints < points){
            setNumberOfPoints(0);
        }
    
    }
    //@XmlTransient
    public List<Badge> getBadges() {
        return badges;
    }
    
    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
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
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.models.Player[ id=" + id + " ]";
    }
    
}
