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
public interface TestDataRulesManagerLocal {

    void addRules(Long appId, String onEventType, int points, Long badgeId);
    
}
