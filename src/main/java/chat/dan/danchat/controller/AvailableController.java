package chat.dan.danchat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.FileNotFoundException;
import java.util.Map;

@RestController
public class AvailableController {
    private Map<String, String> danMojiList;
    public AvailableController(Map<String, String> available) {
        this.danMojiList = available;
    }

    private static final Logger logger = LoggerFactory.getLogger(AvailableController.class);

    @RequestMapping("/available")
    public Map<String, String> greeting() {
        return this.danMojiList;
    }


    @RequestMapping(path="/danmojis/{param}", method=RequestMethod.GET)
    public ResponseEntity<Resource> downloadDanmoji(@PathVariable(value="param") String param) throws FileNotFoundException {
        String path = "./src/main/resources/danmojis/%s";
        File file = new File(String.format(path, param));

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}
