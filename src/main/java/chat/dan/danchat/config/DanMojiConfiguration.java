package chat.dan.danchat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

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
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources(danMojisPathResource.getFilename()+"/*");

		this.availableDanMojis = new HashMap<>();
		String pathTemplate = "/danmojis/%s";

		logger.info(resources.length + " available DanMojis");
		for (Resource danMojiFile : resources) {
			String danMoji = danMojiFile.getFilename().split("\\.(?=[^\\.]+$)")[0];
			logger.info("  " + danMoji);
			this.availableDanMojis.put(danMoji, String.format(pathTemplate, danMojiFile.getFilename()));
		}
	}

	@Bean
	public Map<String, String> getDanMojis() {
		return this.availableDanMojis;
	}
}
