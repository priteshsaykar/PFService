package com.mutualfund.fileupload.service;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.mutualfund.fileupload.model.StockDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockExtractService {

 public List<StockDto> extractFromHtml(String html, String baseUrl) {
     Document doc = Jsoup.parse(html, baseUrl == null ? "" : baseUrl);
     Elements anchors = doc.select("a[href]");
     List<StockDto> result = new ArrayList<>();
     for (Element a : anchors) {
         String name = a.text().trim();
         String href = a.attr("href").trim();
         if (!href.isEmpty() && baseUrl != null && href.startsWith("/")) {
             try {
                 URI base = new URI(baseUrl);
                 URI resolved = base.resolve(href);
                 href = resolved.toString();
             } catch (Exception ignored) {}
         }
         result.add(new StockDto(name, href));
     }
     return result;
 }
}