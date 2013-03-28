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
public interface RulesManagerLocal {

    Long addRule(String onEventType, Long appId, int points, Long badgeId);
    Long addRule(String onEventType, Long appId, Long badgeId);
    Long addRule(String onEventType, Long appId, int points);

    Long removeRule(String ruleId);
    
}
