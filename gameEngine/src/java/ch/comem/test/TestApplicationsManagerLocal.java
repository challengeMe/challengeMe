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
public interface TestApplicationsManagerLocal {

    void addDataTestApplication(String name, String description, String apiKey, String apiSecret);
    
}
