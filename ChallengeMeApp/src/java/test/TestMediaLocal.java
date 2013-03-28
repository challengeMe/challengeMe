/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author bastieneichenberger
 */
@Local
public interface TestMediaLocal {

    void addTestMedia(String titre, String url, String vignetteUrl, double duree);

    void updateTestMedia(Long id, String newTitre, String newUrl, String newVignetteUrl);

    void removeTestMedia(Long id);

    void readTestMedia(Long id);
    
}
