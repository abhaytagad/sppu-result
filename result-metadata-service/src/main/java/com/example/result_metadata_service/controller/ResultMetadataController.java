package com.example.result_metadata_service.controller;

import com.example.result_metadata_service.model.ResultMetadata;
import com.example.result_metadata_service.service.ResultMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
