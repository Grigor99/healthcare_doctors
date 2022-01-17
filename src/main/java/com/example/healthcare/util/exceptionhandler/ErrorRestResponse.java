package com.example.healthcare.util.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
public class ErrorRestResponse extends RestResponse {

    private List<String> errors = new ArrayList<>();

    public ErrorRestResponse(Integer code, String message) {
        errors.add(message);
        super.setCode(code);
        super.setStatus("error");
    }

    public ErrorRestResponse(List<String> errors, Integer code) {
        this.errors = errors;
        super.setCode(code);
        super.setStatus("error");
    }

}

