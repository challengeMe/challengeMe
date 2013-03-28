/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Video;
import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface VideoManagerLocal {

    Long createVideo(String titre, String url, double duree);

    void updateVideo(Long id, String newTitre, String newUrl, double newDuree);

    void removeVideo(Long id);

    Video readVideo(Long id);
    
}
