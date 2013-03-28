/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;

import ch.comem.services.PlayersManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Leo
 */
@Stateless
@WebService
public class TestPlayersManager implements TestPlayersManagerLocal {
    @EJB
    private PlayersManagerLocal playersManager;

    @Override
    public void addPlayer(String firstName, Long appId) {
        
        for(int i=0;i<10;i++){
        
            playersManager.createPlayer("Paul", firstName, "url", appId);
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addBadgeToPlayer(Long idPlayer, Long idBadge) {
        
        playersManager.addBadge(idPlayer, idBadge);
        
    }

    @Override
    public void addPoints(int points, long playerId) {
        playersManager.addPoints(points, playerId);
        
    }

    @Override
    public void removePoints(int points, long playerId) {
        playersManager.removePoints(points, playerId);
    }

    @Override
    public void deletePlayer(long playerID) {
        playersManager.deletePlayer(playerID);
        
    }

    @Override
    public void changePlayer(String firstName, String lastName, String email, Long playerID) {
                
        playersManager.changePlayer(firstName, lastName, email, playerID);
    }


}
