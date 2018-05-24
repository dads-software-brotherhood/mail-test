package mx.infotec.dads.pruebas.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mx.infotec.dads.pruebas.mail.config.ApplicationProperties;
import mx.infotec.dads.pruebas.mail.service.EmailService;
import mx.infotec.dads.pruebas.mail.utils.ValidationUtil;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final static Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	private ApplicationProperties applicationProperties;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		applicationProperties.setHostname(getHostName());

		if (args.length == 1 || args.length == 3) {
			if (ValidationUtil.isMailValid(args[0])) {
				if (args.length == 3) {
					emailService.sendSimpleMessage(args[0], args[1], args[2]);
				} else {
					emailService.sendTestMessage(args[0]);
				}
			} else {
				LOGGER.error("Not valid email address: {}", args[0]);
			}
		} else {
			LOGGER.error("Parameters required.\nUsage: java -jar mail-test.jar <email_address> [subject] [message]");
		}
	}

	private String getHostName() {
		try {
			ProcessBuilder builder = new ProcessBuilder("hostname");
			builder.redirectErrorStream(true);
			Process p = builder.start();
			StringBuilder sb = new StringBuilder();

			try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
				while (br.ready()) {
					if (sb.length() != 0) {
						sb.append('\n');
					}
					sb.append(br.readLine());
				}
			}

			if (p.waitFor() == 0) {
				return sb.toString();
			} else {
				LOGGER.error("Can't exec command:\n{}", sb.toString());
			}
		} catch (InterruptedException | IOException ex) {
			LOGGER.error("Error at retrieve hostname", ex);
		}

		return "-localhost";
	}

}
