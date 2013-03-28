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
 * Jersey REST client generated for REST resource:EventFacadeREST [event]<br>
 * USAGE:
 * <pre>
 *        EventClient client = new EventClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author bastieneichenberger
 */
public class EventClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/gameEngine/resources";

    public EventClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        config.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("event");
    }
    

    public <T> T createEvent_JSON(Class<T> responseType, Object requestEntity) throws UniformInterfaceException {
        return webResource.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(responseType, requestEntity);
    }


    public void close() {
        client.destroy();
    }
    
}
