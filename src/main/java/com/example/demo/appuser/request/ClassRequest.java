package com.example.demo.appuser.request;

import com.example.demo.appuser.model.AppUserRole;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@EqualsAndHashCode
@ToString
public class ClassRequest {
    private final String className;

    @JsonCreator
    public ClassRequest(String className) {
        this.className = className;
    }
}
