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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leo
 */
@Entity
@XmlRootElement
public class Application implements Serializable {
    @OneToOne(mappedBy = "application")
    private LeaderBoard leaderBoard;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String name;
    private String description;
    private String apiKey;
    private String apiSecret;
    @OneToMany(mappedBy = "application")
    private List<Player> players = new LinkedList<Player>();
    
    @OneToMany(mappedBy = "application")
    private List<Rule> rules = new LinkedList<Rule>();

    
    public void addRule(Rule rule){
        if(!getRules().contains(rule)){
        getRules().add(rule);
    }

    
    }
    @XmlTransient
    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
    public void addPlayer(Player player){
        if(!getPlayers().contains(player)){
            getPlayers().add(player);
        }
    
    }
    @XmlTransient
    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }

    public void setLeaderBoard(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }
    
    @XmlTransient
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.models.Application[ id=" + id + " ]";
    }
    
}
