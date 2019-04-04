package it.unisalento.db.project.controllers.rest;

import it.unisalento.db.project.models.dto.GlassdoorJobDetails;
import it.unisalento.db.project.models.dto.MonsterJobDetails;
import it.unisalento.db.project.services.GlassdoorParserService;
import it.unisalento.db.project.services.LinkedinParserService;
import it.unisalento.db.project.services.MonsterParserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class PublicRestController {

    @Autowired
    private LinkedinParserService linkedinParserService;

    @Autowired
    private MonsterParserService monsterParserService;

    @Autowired
    private GlassdoorParserService glassdoorParserService;

    @PostMapping(value = "/linkedin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> parseLinkedInLink(@RequestParam("url") String url) throws IOException {
        return linkedinParserService.parse(url);
    }

    @PostMapping(value = "/monster", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MonsterJobDetails parseMonsterLink(@RequestParam("url") String url) throws IOException {
        return monsterParserService.parse(url);
    }

    @PostMapping(value = "/glassdoor", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject parseGlassdoorLink(@RequestParam("url") String url) throws IOException {
        return glassdoorParserService.parse(url.replace("\"", ""));
    }

}
