/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;

import ch.comem.services.ApplicationsManagerLocal;
import ch.comem.services.BadgesManagerLocal;
import ch.comem.services.EventsManagerLocal;
import ch.comem.services.LeaderBoardsManagerLocal;
import ch.comem.services.PlayersManagerLocal;
import ch.comem.services.RulesManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author bastieneichenberger
 * class qui permet de peuplé la bd de l'engine
 * 
 */
@Stateless
@WebService
public class PopulateBD implements PopulateBDLocal {
    @EJB
    private RulesManagerLocal rulesManager;
    @EJB
    private EventsManagerLocal eventsManager;
    @EJB
    private BadgesManagerLocal badgesManager;
    @EJB
    private PlayersManagerLocal playersManager;
    @EJB
    private LeaderBoardsManagerLocal leaderBoardsManager;
    @EJB
    private ApplicationsManagerLocal applicationsManager;

    @Override
    public void populateDB() {
        Long apID = applicationsManager.createApplication("applicationName", "apDescription", "apiKEY", "secret");
        //leaderBoardsManager.createLeaderBoard("classement", "descriptionClasement", apID);
        // creation des players
        Long bastien = playersManager.createPlayer("bastien", "eichenberger", "bastien@gmail.com", apID);
        Long leo = playersManager.createPlayer("leo", "taillard", "taillad@gmail.com", apID);
        Long cayan = playersManager.createPlayer("cayan", "frey", "frey@gmail.com", apID);
        Long superArbre = playersManager.createPlayer("super", "arbre", "superArbre@gmail.com", apID);
        // créeation des badges
        Long badgeGeeck = badgesManager.createBadge("superGeek", "ce joueur a réussi n mission", "badgegeek");
        Long badgeTM = badgesManager.createBadge("transmédia", "ce joueur a survécu au projet TM", "badgetrans");
        Long badgeCool = badgesManager.createBadge("coolest", "ce joueur est trop cool", "badgecool");
        Long badgeNewbie = badgesManager.createBadge("newbie", "Bienvenue au joueur", "badgenewbie");
        
        // ajout des badges
        playersManager.addBadge(badgeGeeck, bastien);
        playersManager.addBadge(badgeTM, bastien);
        
        playersManager.addBadge(badgeGeeck, leo);
        playersManager.addBadge(badgeTM, cayan);
        playersManager.addBadge(badgeTM, superArbre);
        
        playersManager.addPoints(100, cayan);
        playersManager.addPoints(200, leo);
        playersManager.addPoints(300, bastien);
        playersManager.addPoints(400, superArbre);
        
        //ajout de rules
        
        rulesManager.addRule("test", apID, 100, badgeGeeck);
        rulesManager.addRule("test2", apID, 1000, badgeTM);
        rulesManager.addRule("nouveauMembre", apID, 1000, badgeNewbie);
        rulesManager.addRule("missionValidee", apID, 100);

        //event
        eventsManager.createEvent(cayan, "test", apID);
        eventsManager.createEvent(cayan, "test2", apID);

    }
    
    
    
    
    
    

    

}
