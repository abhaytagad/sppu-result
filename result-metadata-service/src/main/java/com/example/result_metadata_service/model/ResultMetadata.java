package com.example.result_metadata_service.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection= "ResultMetadata")
@Getter @Setter
@AllArgsConstructor
public class ResultMetadata {
    // Getters and Setters
    @Id
    private ObjectId id;
    private String examName;
    private String patternName;
    private String patternId;
    private boolean status;

    // Constructors
    public ResultMetadata() {}


}

