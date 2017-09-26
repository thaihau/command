package tech.th.command;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * Created by thaihau on 26/09/2017.
 */
public class Message implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private String config;

  @NotNull
  private long number;

  @NotNull
  private String content;

  private String result;

  public String getConfig() {
    return config;
  }

  public void setConfig(final String config) {
    this.config = config;
  }

  public long getNumber() {
    return number;
  }

  public void setNumber(final long number) {
    this.number = number;
  }

  public String getContent() {
    return content;
  }

  public void setContent(final String content) {
    this.content = content;
  }

  public String getResult() {
    return result;
  }

  public void setResult(final String result) {
    this.result = result;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Message{");
    sb.append("config='").append(config).append('\'');
    sb.append(", number=").append(number);
    sb.append(", content='").append(content).append('\'');
    sb.append(", result='").append(result).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
