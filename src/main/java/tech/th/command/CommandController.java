package tech.th.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.th.command.config.CommandProperties;

@RestController
@RequestMapping(path = "/command")
public class CommandController {

  private CommandProperties properties;

  public CommandController(final CommandProperties properties) {
    this.properties = properties;
  }

  @PostMapping("/{command}")
  public ResponseEntity<Message> send(@Valid
  @RequestBody Message message, @PathVariable final String command) throws Exception {
    try {

      final String commandFormat = properties.getCommands().get(command);
      System.out.println("sendMessagePattern>>>" + commandFormat);

      if (commandFormat == null) {
        throw new IllegalArgumentException("Invalid command");
      }


      final String commandRequest =
          String.format(commandFormat, message.getConfig(), message.getNumber(),
              message.getContent());
      System.out.println("sendMessageCommand>>>" + commandRequest);
      String result = executeCommand(commandRequest);
      System.out.println("result>" + result);
      message.setResult(result);
      return ResponseEntity.accepted().body(message);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      throw e;
    }
  }

  private static String executeCommand(String command) throws IOException, InterruptedException {

    Process p;
    p = Runtime.getRuntime().exec(command);
    p.waitFor();
    int exitStatus = p.exitValue();

    BufferedReader reader;

    final InputStream is;
    if (exitStatus == 0)
      is = p.getInputStream();
    else is = p.getErrorStream();

    reader =
        new BufferedReader(new InputStreamReader(is));

    StringBuilder output = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      output.append(line).append("\n");
    }

    if (exitStatus != 0) {
      throw new IllegalStateException(output.toString());
    }

    return output.toString();

  }

}
