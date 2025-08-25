package commands;

import errors.BoopError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHelpers {
  public static int getIndexArgument(String input) throws BoopError {
    String[] words = input.split("\\s+", 2);

    if (words.length < 2) { throw new BoopError("Ya missing da index!"); }

    int index;
    try {
        index = Integer.parseInt(words[1]);
    } catch (NumberFormatException e) {
        throw new BoopError("Ya number ain't numbering!");
    }

    return index;
  }
}
