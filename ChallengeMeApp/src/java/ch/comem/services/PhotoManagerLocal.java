/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Photo;
import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface PhotoManagerLocal {

    Long createPhoto(String titre, String url, String vignetteUrl);

    void updatePhoto(Long id, String newTitre, String newUrl, String newVignetteUrl);

    void deletePhoto(Long id);

    Photo readPhoto(Long id);

}
