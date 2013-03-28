/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Media;
import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface MediaManagerLocal {

    Media readMedia(Long mediaId);
    
}
