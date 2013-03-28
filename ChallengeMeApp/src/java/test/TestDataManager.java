/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.services.MembreManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Cayan
 */
@Stateless
@WebService
public class TestDataManager implements TestDataManagerLocal {
    @EJB
    private MembreManagerLocal membreManager;

    @Override
    public void testCreateMembre(String nom, String prenom, String email, int point) {
        for (int i=0; i<10;i++){
            membreManager.createMembre(nom, prenom, email, new Long(1));
        }
    }
    
}
