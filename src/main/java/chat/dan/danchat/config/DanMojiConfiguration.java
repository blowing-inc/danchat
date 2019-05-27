package chat.dan.danchat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Configuration
public class DanMojiConfiguration {
	// private static final String danMojisPath = "./src/main/resources/danmojis";
	private static final Resource danMojisPathResource = new ClassPathResource("danmojis");
	private static final Logger logger = LoggerFactory.getLogger(DanMojiConfiguration.class);

	private Map<String, String> availableDanMojis;

	public DanMojiConfiguration() throws IOException {
		File danMojisPathFile = danMojisPathResource.getFile();
		List<File> availableDanMojiFiles = new ArrayList<>(Arrays.asList(danMojisPathFile.listFiles()));
		this.availableDanMojis = new HashMap<>();

		String pathTemplate = "/danmojis/%s";

		logger.info(availableDanMojiFiles.size() + " available DanMojis");
		for (File danMojiFile : availableDanMojiFiles) {
			String danMoji = danMojiFile.getName().split("\\.(?=[^\\.]+$)")[0];
			logger.info("  " + danMoji);
			this.availableDanMojis.put(danMoji, String.format(pathTemplate, danMojiFile.getName()));
		}
	}

	@Bean
	public Map<String, String> getDanMojis() {
		return this.availableDanMojis;
	}
}
