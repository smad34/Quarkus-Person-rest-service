package de.indibit.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.ws.rs.core.Response;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIException {
    private String message;
    private Response.Status status;
    private LocalDateTime timestamp;
}
