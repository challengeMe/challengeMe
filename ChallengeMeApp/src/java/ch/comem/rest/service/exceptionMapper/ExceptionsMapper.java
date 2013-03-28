/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest.service.exceptionMapper;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author bastieneichenberger
 * Class qui récupère les exceptions de type DAOException
 * 
 */
@Provider
public class ExceptionsMapper implements ExceptionMapper<Exception> {
    /**
     * 
     * @param exception
     * @return une erreur 500
     */
    @Override
    public Response toResponse(Exception e) {
        return Response.status(500).entity(e.getMessage()+"le serveur ne peut pas accéder à cette requête").build();
    }
}

