package app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SaveHandler {
  private final Path savePath;

  public SaveHandler(String savePathName) {
    this.savePath = Paths.get(savePathName);
  }

  public void save(String[] saveStrings) throws IOException {
    if (!Files.exists(savePath)) { 
      Files.createDirectories(savePath.getParent());
    }
    try (BufferedWriter writer = Files.newBufferedWriter(savePath)) {
        for (String line : saveStrings) {
            writer.write(line);
            writer.newLine();
        } 
    }
  }

  public String[] load() throws IOException {
    if (!Files.exists(savePath)) {
      Files.createDirectories(savePath.getParent());
      return new String[0];
    }

    List<String> lines = Files.readAllLines(savePath);

    return lines.toArray(String[]::new);
  }
}
