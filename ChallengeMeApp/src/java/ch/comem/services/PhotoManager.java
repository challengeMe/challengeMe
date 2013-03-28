/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.daoExceptions.DaoException;
import ch.comem.models.Photo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
public class PhotoManager implements PhotoManagerLocal {
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;

    /**
     * méthode qui permet de créer une phooto
     * @param titre
     * @param url
     * @param vignetteUrl
     * @return l'id de la photo créée
     */
    @Override
    public Long createPhoto(String titre, String url, String vignetteUrl) {
        Photo photo = new Photo();
        photo.setTitre(titre);
        photo.setUrl(url);
        photo.setVignetteUrl(vignetteUrl);
        em.persist(photo);
        em.flush();
        return photo.getId();
    }

    /**
     * 
     * @param id, l'id de la photo à modifier
     * @param newTitre, le nouveau titre
     * @param newUrl, la nouvelle url
     * @param newVignetteUrl , la nouvelle url de la vignette
     */
    @Override
    public void updatePhoto(Long id, String newTitre, String newUrl, String newVignetteUrl) {
        Photo retourPhoto = em.find(Photo.class, id);
        if(retourPhoto != null){
            retourPhoto.setTitre(newTitre);
            retourPhoto.setUrl(newUrl);
            retourPhoto.setVignetteUrl(newVignetteUrl);
            em.persist(retourPhoto);
            em.flush();
        }
        else{
            throw new DaoException("la photo n'existe pas", DaoException.StatutsCode.PHOTO_NOT_FOUND);
        } 
    }
    /**
     * 
     * @param id , l'idée de la photo à supprimer
     */
    @Override
    public void deletePhoto(Long id) {
        Photo retourPhoto = em.find(Photo.class, id);
        if(retourPhoto != null){
            em.remove(retourPhoto);            
        }
        else{
            throw new DaoException("la photo n'existe pas", DaoException.StatutsCode.PHOTO_NOT_FOUND);
        }
    }
    /**
     * 
     * @param id, l'id de la photo que l'on veut retourner
     * @return l'objet photo
     */
    @Override
    public Photo readPhoto(Long id) {
        Photo retourPhoto = em.find(Photo.class, id);
        if(retourPhoto == null){
            throw new DaoException("la photo n'existe pas", DaoException.StatutsCode.PHOTO_NOT_FOUND);
        }        
        return retourPhoto;
    }

    
}
