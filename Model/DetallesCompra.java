package com.apiweb.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Compra")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DetallesCompra {
    private ObjectId productoId;
    private ObjectId promocionId;
    private Integer cantidad;
    private Double valorUnidad;
    private List<TallasCompra> Talla = new ArrayList<>();
}
