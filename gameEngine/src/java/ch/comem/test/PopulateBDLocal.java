/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;

import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface PopulateBDLocal {

    void populateDB();
    
}
