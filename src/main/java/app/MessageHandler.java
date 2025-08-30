package app;

import errors.BoopError;

public final class MessageHandler {
    String name = "Boop";

    public String greeting() {
        return """
                Yo! I'm %s. :D
                Wat can ye do for thee, young lass?
                """.formatted(name);
    }

    public String finishLoading() {
        return """
                Tasks loaded miss. :D
                """;
    }

    /**
     * Prints a UI Error message
     * 
     * @param error Error that has occured
     */
    public String errorMessage(BoopError error) {
        return """
                Arr me bad! %s
                """.formatted(error.getMessage());
    }
}
