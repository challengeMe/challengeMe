/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Application;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leo
 */
@Stateless
public class ApplicationsManager implements ApplicationsManagerLocal {
    @PersistenceContext(unitName = "challengeMePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    /**
     * méthode qui permet d'ajouter un applicaiton
     * @param name
     * @param description
     * @param apiKey
     * @param apiSecret
     * @return l'id de l'application
     */
    @Override
    public Long createApplication(String name, String description, String apiKey, String apiSecret) {
        Application application = new Application();
        
        
        application.setName(name);
        application.setDescription(description);
        application.setApiKey(apiKey);
        application.setApiSecret(apiSecret);
        
        em.persist(application);
        em.flush();
        
        return application.getId();
    }
    
    /**
     * méthode qui permet de supprimer une application
     * @param appId
     * @return 
     */
    @Override
    public Long removeApplication(String appId) {
        Application app = em.find(Application.class, appId);
        
        em.remove(app);
        em.flush();
        
        return app.getId();
    }
    
    /**
     * méthode qui permet d'updeter une application
     * @param name
     * @param description
     * @param apiKey
     * @param apiSecret
     * @param appId
     * @return l'id de l'applicaiton
     */
    @Override
    public Long changeApplication(String name, String description, String apiKey, String apiSecret, String appId) {
        
        Application application = em.find(Application.class, appId);
        
        application.setName(name);
        application.setDescription(description);
        application.setApiKey(apiKey);
        application.setApiSecret(apiSecret);
        
        em.persist(application);
        em.flush();
        
        return application.getId();
    }

}
