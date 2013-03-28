package ch.comem.rest.service.exceptionMapper;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import ch.comem.daoExceptions.DaoException;
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
public class DAOExceptionMapper implements ExceptionMapper<DaoException> {
    /**
     * 
     * @param exception
     * @return une erreur 404 - NOT FOUND ainsi que le message du DAO
     */
    @Override
    public Response toResponse(DaoException exception) {
        return Response.status(400).entity(exception.getMessage()).build();
    }
}
