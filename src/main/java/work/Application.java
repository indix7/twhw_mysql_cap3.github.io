package work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  final
  Console console;

  public Application(HandService handService, Console console) {
    this.console = console;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

  }

}
