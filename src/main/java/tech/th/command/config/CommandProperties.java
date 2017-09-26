package tech.th.command.config;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by thaihau on 26/09/2017.
 */
@ConfigurationProperties(prefix = "command", ignoreUnknownFields = false)
public class CommandProperties {

  private HashMap<String, String> commands;

  public HashMap<String, String> getCommands() {
    return commands;
  }

  public void setCommands(final HashMap<String, String> commands) {
    this.commands = commands;
  }

}
