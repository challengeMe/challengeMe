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
public interface EventsManagerLocal {

    Long createEvent(Long playerId, String type, Long applicationId);

    Long removeEvent(Long eventId);

    Long changeEvent(String type, Long eventID);
    
}
