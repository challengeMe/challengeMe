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
public interface TestDataLeaderBoardManagerLocal {

    void createLeaderBoard(String name, String description, Long appId);
    
}
