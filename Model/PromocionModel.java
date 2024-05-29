package com.apiweb.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Promocion")
@TypeAlias ("PRomociones")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PromocionModel {
    @Id
    private ObjectId id;
    private String descripcion;
    private List<ProductosConPromocion> ProductosConPromocion = new ArrayList<>();
}
