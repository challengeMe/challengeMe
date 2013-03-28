/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;

import java.sql.Date;
import javax.ejb.Local;

/**
 *
 * @author Leo
 */
@Local
public interface TestEventsManagerLocal {


    void createEvent(Long playerId, String type, Long applicationId);

    void removeEvents(Long eventId);

    void changeEvent(String type, Long eventId);
    
}
