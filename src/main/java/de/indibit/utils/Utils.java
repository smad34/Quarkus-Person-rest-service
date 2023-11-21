package de.indibit.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    public static Long extractIdFromLocationHeader(String locationHeader) {
        // Assuming the location header is in the format "/persons/{id}"
        String[] parts = locationHeader.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }

    public static String convertToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
