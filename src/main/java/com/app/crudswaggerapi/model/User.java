package com.app.crudswaggerapi.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    private UUID userId;
    private String firstName;
    private String lastNamee;
}
