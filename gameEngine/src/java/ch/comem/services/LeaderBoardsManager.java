/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Application;
import ch.comem.models.LeaderBoard;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leo
 */
@Stateless
public class LeaderBoardsManager implements LeaderBoardsManagerLocal {
    @PersistenceContext(unitName = "challengeMePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    /**
     * méthode qui permet de créer un LeaderBoard
     * @param name
     * @param description
     * @param applicationId
     * @return l'id du leaderBoard
     */
    @Override
    public Long createLeaderBoard(String name, String description, Long applicationId) {
        
        LeaderBoard lead = new LeaderBoard();
        Application app = em.find(Application.class, applicationId);
        
        app.setLeaderBoard(lead);
        
        lead.setName(name);
        lead.setDescription(description);
        lead.setApplication(app);
        
        em.persist(lead);
        em.flush();
        
        return lead.getId();
    }
    
    /**
     * méthode qui permet de supprimer un leaderBoard
     * @param leaderBoardId
     * @return 
     */
    @Override
    public Long deleteLeaderboard(Long leaderBoardId) {
        
        LeaderBoard lead = em.find(LeaderBoard.class, leaderBoardId);
        
        em.remove(lead);
        em.flush();
        
        return lead.getId();
    }


}
