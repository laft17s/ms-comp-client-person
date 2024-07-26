package com.laft17s.mscompclientperson.utils;

import com.laft17s.mscompclientperson.dto.GenericResponseDTO;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ClientPersonUtils {

    public static GenericResponseDTO mapServiceResponse (Integer code, GenericResponseDTO.Status status, Object payload, String message) {

        List<Object> list;
        if (payload == null) {
            list = Collections.emptyList();
        } else if (payload instanceof List) {
            list = (List<Object>) payload;
        } else {
            list = Collections.singletonList(payload);
        }

        return GenericResponseDTO.builder()
                .code(code)
                .status(status)
                .payload(list)
                .message(message)
                .build();
    }

}
