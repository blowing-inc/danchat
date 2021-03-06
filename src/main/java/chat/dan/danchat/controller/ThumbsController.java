package chat.dan.danchat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@RestController
public class ThumbsController {
    private Map<String, String> danMojiList;
    public ThumbsController(Map<String, String> thumbs) {
        this.danMojiList = thumbs;
    }

    private static final Logger logger = LoggerFactory.getLogger(ThumbsController.class);

    @RequestMapping("/thumbs")
    public Map<String, String> greeting() {
        return this.danMojiList;
    }


    @RequestMapping(path="/thumbs/{param}", method=RequestMethod.GET)
    public ResponseEntity<Resource> downloadDanmoji(@PathVariable(value="param") String param) throws IOException {
        String path = "thumbs/%s";
        File file = new ClassPathResource(String.format(path, param)).getFile();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}
