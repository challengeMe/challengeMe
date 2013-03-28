/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;

import ch.comem.services.RulesManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Leo
 */
@Stateless
@WebService
public class TestDataRulesManager implements TestDataRulesManagerLocal {
    @EJB
    private RulesManagerLocal rulesManager;

    @Override
    public void addRules(Long appId, String onEventType, int points, Long badgeId) {
        
        rulesManager.addRule(onEventType, appId, points, badgeId);
    }


}
