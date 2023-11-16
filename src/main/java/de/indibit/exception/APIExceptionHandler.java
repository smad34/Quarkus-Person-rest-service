package de.indibit.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class APIExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof RequestException) {
            return handleRequestException((RequestException) exception);
        } else if (exception instanceof EntityNotFoundException) {
            return handleEntityNotFoundException((EntityNotFoundException) exception);
        }
        else {
            APIException apiException = new APIException("internal server error",
                    INTERNAL_SERVER_ERROR, LocalDateTime.now());
            return Response.status(apiException.getStatus())
                    .entity(apiException).build();
        }
    }

    public Response handleRequestException(RequestException e) {
        APIException exception = new APIException(e.getMessage(),
                e.getStatus(), LocalDateTime.now());
        return Response.status(e.getStatus())
                .entity(exception).build();
    }

    public Response handleEntityNotFoundException(EntityNotFoundException e) {
        APIException exception = new APIException(e.getMessage(),
                NOT_FOUND, LocalDateTime.now());
        return Response.status(exception.getStatus())
                .entity(exception).build();
    }

}