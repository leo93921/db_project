package it.unisalento.db.project.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class LinkedinParserService {

    public Map<String, String> parse(String url) throws IOException {
        Map<String, String> parsedItems = new HashMap<>();

        Document doc = Jsoup.connect(url).get();

        // Extract position name
        Elements h1s = doc.getElementsByTag("h1");
        parsedItems.put("name", h1s.get(0).html());

        // Extract criteria item
        Elements jobCriteria = doc.getElementsByClass("job-criteria__item");
        for (Element e : jobCriteria) {
            parsedItems.put(e.child(0).html(), e.child(1).html());
        }

        // Extract description as text
        Elements richDescriptions = doc.getElementsByClass("description__text--rich");
        StringBuilder stringBuilder = new StringBuilder();
        for (Element p : richDescriptions.get(0).children()) {
            stringBuilder.append(p.text() + "\n");
        }
        parsedItems.put("description", stringBuilder.toString());

        return parsedItems;
    }

}
