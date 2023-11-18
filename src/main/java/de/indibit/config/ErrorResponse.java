package de.indibit.config;


import de.indibit.domain.Person;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Getter;
import lombok.Setter;
/**
 * <b>Title:</b> ErrorResponse <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@Getter
@Setter
public class ErrorResponse {

    @JsonbProperty("error_code")
    private String errorCode;

    @JsonbProperty("error_message")
    private String errorMessage;

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}