/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.engineException.EngineException;
import ch.comem.models.Application;
import ch.comem.models.Badge;
import ch.comem.models.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leo
 */
@Stateless
public class PlayersManager implements PlayersManagerLocal {
    @PersistenceContext(unitName = "challengeMePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    /**
     * méthode qui permet de créer un player
     * @param firstName
     * @param lastName
     * @param email
     * @param appId
     * @return l'id du player
     */
    @Override
    public Long createPlayer(String firstName, String lastName, String email, Long appId) {
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEmail(email);
        player.setNumberOfPoints(0);
        
        Application app = em.find(Application.class, appId);
        //app.getLeaderBoard().addPlayer(player);
        
        player.setApplication(app);
        app.addPlayer(player);
        
        em.persist(player);
        em.flush();
        
        return player.getId();
    }
    
    /**
     * méthode qui permet de lire un player
     * @param id
     * @return un objet de type player
     */
    @Override
    public Player readPlayer(Long id) {
        Player retourPlayer = em.find(Player.class, id);
        if(retourPlayer == null){
            throw new EngineException("le player n'existe pas", EngineException.StatutsCode.PLAYER_NOT_FOUND);
        }
        return retourPlayer;
    }
    
    /**
     * méthode qui permet de mettre à jour un player
     * @param firstName
     * @param lastName
     * @param email
     * @param playerID
     * @return 
     */
    @Override
    public Long changePlayer(String firstName, String lastName, String email, Long playerID) {
        
        Player player = em.find(Player.class, playerID);
        
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEmail(email);
        
        em.persist(player);
        em.flush();
        
        return player.getId();

    }
    
    /**
     * méthode qui permet de supprimer un player
     * @param playerID 
     */
    @Override
    public void deletePlayer(long playerID) {
        Player player = em.find(Player.class, playerID);
        
        em.remove(player);
        
    }
    
    /**
     * méthode qui permet d'ajouter un badge
     * @param badgeId
     * @param playerId
     * @return 
     */
    @Override
    public Long addBadge(Long badgeId, Long playerId) {
        
        Player player = em.find(Player.class, playerId);
        Badge badge = em.find(Badge.class, badgeId);
        
        player.addBadge(badge);
        badge.addPlayer(player);
        
        em.persist(player);
        em.persist(badge);
        

        em.flush();
        
        return player.getId();
    }
    
    /**
     * méthode qui permet d'ajouter des points
     * @param points
     * @param playerId
     * @return le nombre de points ajoutés
     */
    @Override
    public int addPoints(int points, long playerId) {
        Player player = em.find(Player.class, playerId);
        player.addPoints(points);
        
        em.persist(player);
        
        em.flush();
        
        
        return player.getNumberOfPoints();
    }
    
    /**
     * méthode qui permet de supprimer des points
     * @param points
     * @param playerId
     * @return 
     */
    @Override
    public int removePoints(int points, long playerId) {
        Player player = em.find(Player.class, playerId);
        player.removePoints(points);
        
        em.persist(player);
        
        em.flush();
        
        return player.getNumberOfPoints();
    }
    
    
    @Override
    public List<Badge> getListBadges(Long playerId){
        List<Badge> ret = null;
        Player player = this.readPlayer(playerId);
        ret = player.getBadges();
        if(ret.isEmpty()){
            throw new EngineException("le player n'a aucun badges", EngineException.StatutsCode.BADGE_NOT_FOUND);
        }
        return ret;
    }





    
    


}
