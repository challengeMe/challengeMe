/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.daoExceptions.DaoException;
import ch.comem.models.Media;
import ch.comem.models.Photo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
public class MediaManager implements MediaManagerLocal {
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;
    
    
    /**
     * méthode qui permet de lire un média
     * @param mediaId
     * @return un objet média
     */
    @Override
    public Media readMedia(Long mediaId) {
        Media retourMedia = em.find(Media.class, mediaId);
        if(retourMedia == null){
            throw new DaoException("le media n'existe pas", DaoException.StatutsCode.MEDIA_NOT_FOUND);
        }        
        return retourMedia;
    }

}
