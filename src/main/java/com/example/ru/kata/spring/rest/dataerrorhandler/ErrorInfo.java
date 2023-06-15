package com.example.ru.kata.spring.rest.dataerrorhandler;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ErrorInfo {
    private String errI;

    public ErrorInfo() {
    }
    public ErrorInfo(String errI) {
        this.errI = errI;
    }

    public String getErrI() {
        return errI;
    }
}
