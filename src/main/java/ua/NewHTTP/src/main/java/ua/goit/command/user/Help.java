package ua.goit.command.user;

import ua.goit.command.Command;
import ua.goit.view.View;

import java.io.IOException;

public class Help  implements Command {

  private View view;
  public Help(View view) {
      this.view = view;
  }

    @Override
    public void process() throws IOException {
        view.write("help - show a list of commands");
        view.write("user -create - create a user");
        view.write("user -create1 - create a user with array");
        view.write("user -get1 - get user by username");
        view.write("user -update - update the user");
        view.write("user -delete - delete the user");
        view.write("user -login - log in");
        view.write("user -logout - log out");
        view.write("pet -create - create pet");
        view.write("pet -get - get pet by id");
        view.write("pet -get1 - get pets by status");
        view.write("pet -delete - delete pet");
        view.write("pet -update - update pet");
        view.write("pet -update1 - update pet with form data");
        view.write("pet -img-upload - upload pet's image");
        view.write("order -create - place order");
        view.write("inventory - return inventory");
        view.write("order -get - find order by id");
        view.write("order -delete - delete order by ID");
    }

    @Override
    public String commandName() {
        return "help";
    }


}
