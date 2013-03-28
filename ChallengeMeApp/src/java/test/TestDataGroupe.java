/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ch.comem.services.GroupeManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Cayan
 */
@Stateless
@WebService
public class TestDataGroupe implements TestDataGroupeLocal {
    @EJB
    private GroupeManagerLocal groupeManager;

    @Override
    public void testCreateGroupe(String nom) {
        for (int i=0; i<10;i++){
            //groupeManager.createGroupe(nom);
        }
    }
    
    

    
}
