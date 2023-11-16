package de.indibit.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.Response;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestException extends RuntimeException {
    private String message;
    private Response.Status status;
}
