package chat.dan.danchat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DanMojiConfiguration {
	private static final String danMojisPath = "./src/main/resources/danmojis";
	private static final Logger logger = LoggerFactory.getLogger(DanMojiConfiguration.class);

	private List<String> availableDanMojis;

	public DanMojiConfiguration() {
		File danMojisPathFile = new File(danMojisPath);
		List<File> availableDanMojiFiles = new ArrayList<>(Arrays.asList(danMojisPathFile.listFiles()));
		this.availableDanMojis = new ArrayList<>();

		logger.info(availableDanMojiFiles.size() + " available DanMojis");
		for (File danMojiFile : availableDanMojiFiles) {
			String danMoji = danMojiFile.getName().split("\\.(?=[^\\.]+$)")[0];
			logger.info("  " + danMoji);
			this.availableDanMojis.add(danMoji);
		}
	}

	@Bean
	public List<String> getDanMojis() {
		return this.availableDanMojis;
	}
}
