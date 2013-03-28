/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import javax.ws.rs.core.MediaType;

/**
 * Jersey REST client generated for REST resource:PlayerFacadeREST [player]<br>
 * USAGE:
 * <pre>
 *        PlayerClient client = new PlayerClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author bastieneichenberger
 */
public class PlayerClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/gameEngine/resources";

     public PlayerClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        config.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
        client = Client.create(config);
        // init propriétées
        webResource = client.resource(BASE_URI).path("player");
    }

    public void remove(String id) throws UniformInterfaceException {
        webResource.path(java.text.MessageFormat.format("{0}", new Object[]{id})).delete();
    }

    public void edit_XML(Object requestEntity) throws UniformInterfaceException {
        webResource.type(javax.ws.rs.core.MediaType.APPLICATION_XML).put(requestEntity);
    }

    public void edit_JSON(Object requestEntity) throws UniformInterfaceException {
        webResource.type(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(requestEntity);
    }

    public <T> T createPlayer_JSON(Class<T> responseType, Object requestEntity) throws UniformInterfaceException {
        return webResource.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(responseType, requestEntity);
    }

    public <T> T getPlayer_XML(Class<T> responseType, String id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getPlayer_JSON(Class<T> responseType, String id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.destroy();
    }
    
}
