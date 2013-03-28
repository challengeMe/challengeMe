/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;

import ch.comem.services.ApplicationsManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Leo
 */
@Stateless
@WebService
public class TestApplicationsManager implements TestApplicationsManagerLocal {
    @EJB
    private ApplicationsManagerLocal applicationsManager;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void addDataTestApplication(String name, String description, String apiKey, String apiSecret) {
        
        for (int i = 0; i < 10; i++) {
            
            applicationsManager.createApplication(name, description, apiKey + i, apiSecret+i);
            
        }
        
    }

}
