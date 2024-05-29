package com.apiweb.backend.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.ENUM.Tallas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TallasCompra {
    private Tallas talla;
    private Integer cantidadCompradaPorTalla;
}
