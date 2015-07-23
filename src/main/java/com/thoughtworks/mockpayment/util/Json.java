package com.thoughtworks.mockpayment.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

    public static final ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(Object obj) {

        String jsonString;
        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return jsonString;
    }
}
