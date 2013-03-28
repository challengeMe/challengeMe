/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.ejb.Local;

/**
 *
 * @author Cayan
 */
@Local
public interface TestDataGroupeLocal {

    void testCreateGroupe(String nom);
    
}
