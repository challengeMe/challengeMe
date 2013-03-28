/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import javax.ejb.Local;

/**
 *
 * @author Leo
 */
@Local
public interface LeaderBoardsManagerLocal {

    Long createLeaderBoard(String name, String description, Long applicationId);

    Long deleteLeaderboard(Long leaderBoardId);
    
}
