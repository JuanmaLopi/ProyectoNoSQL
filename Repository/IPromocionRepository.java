package com.apiweb.backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.PromocionModel;

public interface IPromocionRepository extends MongoRepository<PromocionModel,ObjectId>{
    
}
