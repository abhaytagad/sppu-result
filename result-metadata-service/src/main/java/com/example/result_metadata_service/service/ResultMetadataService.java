package com.example.result_metadata_service.service;

import com.example.result_metadata_service.model.ResultMetadata;
import com.example.result_metadata_service.repository.ResultMetadataRepository;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultMetadataService {

    private static final String METADATA_URL = "https://onlineresults.unipune.ac.in/Result/Dashboard/Default";
    @Autowired
    private ResultMetadataRepository repo;
    @PostConstruct
    public void init() {
        disableSSLCertificateChecking(); // Enable unsafe SSL for JSoup
    }

    public List<ResultMetadata> fetchResultMetadata() {
        List<ResultMetadata> results = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(METADATA_URL)
                    .userAgent("Mozilla/5.0")
                    .referrer("https://www.google.com")
                    .timeout(1000000)
                    .ignoreHttpErrors(true)
                    .get();



            for (Element row : doc.select("tr")) {
                Elements tds = row.select("td");
                if (tds.size() < 4) continue; // Skip if structure is not as expected

                String examName = tds.get(1).text(); // 2nd column = Exam Name
                String onclickAttr = tds.get(3).selectFirst("a").attr("onclick");

                // Extract values from: Enterdetails('patternName','patternId')
                String patternName = "";
                String patternId = "";
                try {
                    String[] params = onclickAttr.substring(
                            onclickAttr.indexOf("(") + 1,
                            onclickAttr.indexOf(")")
                    ).replaceAll("'", "").split(",");
                    patternName = params[0];
                    patternId = params[1];
                } catch (Exception e) {
                    e.printStackTrace(); // Safe fallback
                }

                ResultMetadata metadata = new ResultMetadata();
                metadata.setExamName(examName);
                metadata.setPatternName(patternName);
                metadata.setPatternId(patternId);
                metadata.setStatus(true);

                results.add(metadata);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        repo.deleteAll();
        repo.saveAll(results);
        return results;
    }

    private void disableSSLCertificateChecking() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                    }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ResultMetadata> fetchResultMetadataFromDB() {
        return repo.findAll();
    }
}
