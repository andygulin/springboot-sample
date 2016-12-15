package springboot.sample;

import org.springframework.boot.CommandLineRunner;

public class ApplicationStartupRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Startup...");
	}
}