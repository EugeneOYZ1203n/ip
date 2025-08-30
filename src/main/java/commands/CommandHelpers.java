package commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import errors.BoopError;

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

  public static class Flags {
    private final Map<String, String> values = new HashMap<>();
    private final Map<String, String> aliasToCanonical = new HashMap<>();

    public static Flags parseFlags(
            Map<String, List<String>> definitions, 
            String input) {
        return new Flags(definitions, input);
    }

    private Flags(Map<String, List<String>> definitions, String input) throws BoopError {
        // Build alias lookup, actual flag + list of possible options
        for (Map.Entry<String, List<String>> e : definitions.entrySet()) {
            String canonical = e.getKey();
            for (String alias : e.getValue()) {
                aliasToCanonical.put(alias, canonical);
            }
        }

        // parts is all the flags and the arguments used
        String[] sections = input.split("\\s+", 2);

        if (sections.length < 2) {
          return;
        }

        String[] parts = sections[1].split(" /(?=\\w+)");
        // this is the argument with no flag
        String noFlagArg = parts[0].trim();
        if (!noFlagArg.isEmpty()) {
            values.put("", noFlagArg);
        }

        for (int i = 1; i < parts.length; i++) {
          String[] flagSplit = parts[i].split("\\s+", 2);
          // first value is the name of the flag "/" was already removed by split
          String rawFlag = flagSplit[0];
          // everything else is the value
          String value = (flagSplit.length > 1) ? flagSplit[1].trim() : "";

          // Convert aliases to canonical
          String canonical = aliasToCanonical.get(rawFlag);
          if (canonical == null) {
              throw new BoopError("Unknown flag was used: " + rawFlag);
          }
          if (values.containsKey(canonical)) {
              throw new BoopError("Duplicate flag was used: " + rawFlag);
          }
          values.put(canonical, value);
        }
      }

      public String get(String flag) {
        return values.get(flag);
      }

      public boolean has(String flag) {
        return values.containsKey(flag);
      }
  }


}
