package de.indibit.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        APIException apiException = new APIException(NOT_FOUND.getReasonPhrase(),
                NOT_FOUND, LocalDateTime.now());
        return Response.status(apiException.getStatus())
                .entity(apiException).build();
    }

}