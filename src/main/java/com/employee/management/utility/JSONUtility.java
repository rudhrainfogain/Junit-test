package com.employee.management.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtility {

    public static String stringify(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String output = null;
        try {
            output = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            output = obj.toString();
        }
        return output;
    }
}
