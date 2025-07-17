package com.example.result_metadata_service.repository;

import com.example.result_metadata_service.model.ResultMetadata;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultMetadataRepository extends MongoRepository<ResultMetadata, ObjectId> {
}
