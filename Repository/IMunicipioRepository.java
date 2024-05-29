package com.apiweb.backend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.MunicipioModel;

public interface IMunicipioRepository extends MongoRepository<MunicipioModel,Integer>{
    
}
