/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models.DTO;

import ch.comem.daoExceptions.RESTException;
import ch.comem.models.Membre;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Bastien Eichenberger
 */
@XmlRootElement
public class MembreDTO implements Serializable {
   
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long playerId;
    private Long numberOfPoints;
  
    
    private List<BadgeDTO> badges = new LinkedList<BadgeDTO>();
    
    public MembreDTO(){
    }
    
    public MembreDTO(Membre membre, JSONObject playerJson){
        try {
            this.id = membre.getId();
            this.firstName = membre.getNom();
            this.lastName = membre.getPrenom();
            this.email = membre.getEmail();
            
            this.playerId = playerJson.getLong("id");
            this.numberOfPoints = playerJson.getLong("numberOfPoints");
            
            Object badgesPlayer = playerJson.get("badges");
            if(badgesPlayer instanceof JSONObject){
                JSONObject badgeObject = (JSONObject) badgesPlayer;
                BadgeDTO badge = new BadgeDTO();
                badge.setDescription(badgeObject.getString("description"));
                badge.setTitle(badgeObject.getString("title"));
                badge.setIcon(badgeObject.getString("icon"));
                badge.setId(badgeObject.getLong("id"));
                this.badges.add(badge);
            }
            else if (badgesPlayer instanceof JSONArray){
                JSONArray array = (JSONArray) badgesPlayer;
                for(int i=0; i<array.length(); i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    BadgeDTO badge = new BadgeDTO();                    
                    badge.setDescription(jsonObject.getString("description"));
                    badge.setTitle(jsonObject.getString("title"));
                    badge.setIcon(jsonObject.getString("icon"));
                    badge.setId(jsonObject.getLong("id"));
                    this.badges.add(badge);
                }
            }
            else{
            }
        } catch (JSONException e) {
            throw new RESTException("problème22 lors de la création de l'objet JSON: "+e.getMessage());
        }
    }
    public List<BadgeDTO> getBadges() {
        return badges;
    }

    public void setBadges(List<BadgeDTO> badges) {
        this.badges = badges;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Long numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
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
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.services.Membre[ id=" + id + " ]";
    }
}