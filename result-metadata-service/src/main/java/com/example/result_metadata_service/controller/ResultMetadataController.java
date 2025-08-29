package com.example.result_metadata_service.controller;

import com.example.result_metadata_service.model.ResultMetadata;
import com.example.result_metadata_service.service.ResultMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/metadata")

public class ResultMetadataController {

    @Autowired
    private ResultMetadataService metadataService;

    @GetMapping("/allfromdb")
    public List<ResultMetadata> getAllResultMetadataFormDB() {
        return metadataService.fetchResultMetadataFromDB();
    }

    @GetMapping("/all")
    public List<ResultMetadata> getAllResultMetadata() {
        return metadataService.fetchResultMetadata();
    }

}
