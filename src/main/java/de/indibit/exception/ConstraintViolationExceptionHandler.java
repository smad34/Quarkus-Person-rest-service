package de.indibit.exception;

import org.hibernate.exception.ConstraintViolationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        APIException apiException = new APIException("invalid input",
                BAD_REQUEST, LocalDateTime.now());
        return Response.status(apiException.getStatus())
                .entity(apiException).build();
    }

}