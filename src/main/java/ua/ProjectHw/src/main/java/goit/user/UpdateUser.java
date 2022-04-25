package goit.user;


import goit.client.HttpClientUtil;
import goit.client.PetstoreHttpClient;
import goit.entity.User;
import goit.util.UserUtil;
import goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class UpdateUser implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public UpdateUser(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        UserUtil userUtil = new UserUtil();
        view.write("Enter username for user, which should be updated:");
        String username = view.read();
        view.write("Enter updated data: ");
        User user = userUtil.createUserThrowConsole();
        updateUser(username, user);
    }

    @Override
    public String commandName() {
        return "user -update";
    }

    public void updateUser(String name, User user) {
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareUpdateWithData(
                    PetstoreHttpClient.getUserEndPoint(), name, user), HttpResponse.BodyHandlers.ofString());
            if(responseOfGet.statusCode() == 200) {
                System.out.println("The User was updated successful \n" + responseOfGet.body());
            }else{
                System.out.println(responseOfGet.statusCode() + responseOfGet.body());
            }
        }catch (IOException | InterruptedException io){
            io.printStackTrace();
        }
    }
}

