/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Badge;
import ch.comem.models.Player;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Leo
 */
@Local
public interface PlayersManagerLocal {

    Long createPlayer(String firstName, String lastName, String email, Long appId);
    
    Long changePlayer(String firstName, String lastName, String email, Long playerID);

    Long addBadge(Long badgeId, Long playerId);

    int addPoints(int points, long playerId);

    int removePoints(int points, long playerId);

    void deletePlayer(long playerID);
    
    Player readPlayer(Long id);
    
    List<Badge> getListBadges(Long playerId);



    
}
