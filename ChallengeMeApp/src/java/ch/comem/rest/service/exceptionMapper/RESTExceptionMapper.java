package ch.comem.rest.service.exceptionMapper;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ch.comem.daoExceptions.RESTException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author bastieneichenberger
 * 
 */
@Provider
public class RESTExceptionMapper implements ExceptionMapper<RESTException> {

    @Override
    public Response toResponse(RESTException exception) {
        return Response.status(400).entity(exception.getMessage()).build();
    }
}
