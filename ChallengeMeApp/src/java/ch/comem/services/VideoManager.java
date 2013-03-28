/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.daoExceptions.DaoException;
import ch.comem.models.Video;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bastieneichenberger
 */
@Stateless
public class VideoManager implements VideoManagerLocal {
    @PersistenceContext(unitName = "challengeMeAppPU")
    private EntityManager em;

    /**
     * Méthode qui permet de créer une vidéo
     * @param titre
     * @param url
     * @param duree
     * @return l'id de la vidéo
     */
    @Override
    public Long createVideo(String titre, String url, double duree) {
        Video video = new Video();
        video.setTitre(titre);
        video.setUrl(url);
        video.setDuree(duree);
        em.persist(video);
        em.flush();
        return video.getId();
    }

    /**
     * 
     * @param id
     * @param newTitre
     * @param newUrl
     * @param newDuree 
     */
    @Override
    public void updateVideo(Long id, String newTitre, String newUrl, double newDuree) {
        Video retourVideo = em.find(Video.class, id);
        if(retourVideo != null){
            retourVideo.setTitre(newTitre);
            retourVideo.setUrl(newUrl);
            retourVideo.setDuree(newDuree);
            em.persist(retourVideo);
            em.flush();
        }
        else{
            throw new DaoException("la video n'existe pas", DaoException.StatutsCode.VIDEO_NOT_FOUND);
        } 
    }
    /**
     * méthode qui permet de supprimer une vidéo
     * @param id 
     */
    @Override
    public void removeVideo(Long id) {
        Video retourVideo = em.find(Video.class, id);
        if(retourVideo != null){
            em.remove(retourVideo);
        }
        else{
            throw new DaoException("la video n'existe pas", DaoException.StatutsCode.VIDEO_NOT_FOUND);
        }
    }
    /**
     * méthode qui permet de lire une vidéo
     * @param id
     * @return 
     */
    @Override
    public Video readVideo(Long id) {
        Video retourVideo = em.find(Video.class, id);
        if(retourVideo == null){
            throw new DaoException("la video n'existe pas", DaoException.StatutsCode.VIDEO_NOT_FOUND);
        }
        return retourVideo;
    }
    
    

    

}
