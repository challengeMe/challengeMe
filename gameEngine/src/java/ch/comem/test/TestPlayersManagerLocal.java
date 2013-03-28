/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;

import javax.ejb.Local;

/**
 *
 * @author Leo
 */
@Local
public interface TestPlayersManagerLocal {

    void addPlayer(String firstName, Long appId);

    void addBadgeToPlayer(Long idPlayer, Long idBadge);

    void addPoints(int points, long playerId);

    void removePoints(int points, long playerId);

    void deletePlayer(long playerID);

    void changePlayer(String firstName, String lastName, String email, Long playerID);

    
}
