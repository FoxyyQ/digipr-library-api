package rocks.process.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"rocks.process.library", "rocks.process.library.api","rocks.process.library.business.service","rocks.process.library.config","rocks.process.library.data.domain","rocks.process.library.data.repository;"})
public class LibraryApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(LibraryApiApplication.class, args);
    }
  }

