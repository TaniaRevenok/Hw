package goit.user;

import java.io.IOException;

public interface Command {
    void process() throws IOException;

    String commandName();

    default boolean canProcess(String command) {
        return commandName().equals(command);
    }
}
