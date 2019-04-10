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

	public List<File> availableDanMojis;

	public DanMojiConfiguration() {
		File danMojisPathFile = new File(danMojisPath);
		availableDanMojis = new ArrayList<>(Arrays.asList(danMojisPathFile.listFiles()));

		logger.info(availableDanMojis.size() + " available DanMojis");
		for (File danMoji : availableDanMojis) {
			logger.info("  =>" + danMoji);
		}
	}

	@Bean
	public List<File> getDanMojis() {
		return availableDanMojis;
	}
}
