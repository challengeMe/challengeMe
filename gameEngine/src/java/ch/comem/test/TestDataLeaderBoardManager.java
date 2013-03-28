/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;

import ch.comem.services.LeaderBoardsManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Leo
 */
@Stateless
@WebService
public class TestDataLeaderBoardManager implements TestDataLeaderBoardManagerLocal {
    @EJB
    private LeaderBoardsManagerLocal leaderBoardsManager;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void createLeaderBoard(String name, String description, Long appId) {
        leaderBoardsManager.createLeaderBoard(name, description, appId);
        
    }

}
