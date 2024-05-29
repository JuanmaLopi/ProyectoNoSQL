package com.apiweb.backend.Model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DetallesEnvio {
    private Date fechaEnvio;
    private String direccion;
    private Integer municipioId;
}
