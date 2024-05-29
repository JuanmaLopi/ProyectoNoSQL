package com.apiweb.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Municipio")
@TypeAlias ("Municipios")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MunicipioModel {
    @Id
    private Integer id;
    private String nombre;    
}
