/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Badge;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leo
 */
@Stateless
public class BadgesManager implements BadgesManagerLocal {
    @PersistenceContext(unitName = "challengeMePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    /**
     * méthode qui permet de créer un badge
     * @param title
     * @param description
     * @param icon
     * @return l'id du badge créé
     */
    @Override
    public Long createBadge(String title, String description, String icon) {
        
        Badge badge = new Badge();
        
        //
        badge.setTitle(title);
        badge.setDescription(description);
        badge.setIcon(icon);
        
        em.persist(badge);
        em.flush();
        
        return badge.getId();
        
    }
    
    /**
     * méthode qui permet de supprimer un badge
     * @param badgeId
     * @return l'id du badge
     */
    @Override
    public Long removeBadge(Long badgeId) {
        Badge badge = em.find(Badge.class, badgeId);
        em.remove(badge);
        em.flush();
        return badge.getId();
    }

    
    

}
