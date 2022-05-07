package ua.goit.command.user;

import ua.goit.client.PetstoreHttpClient;
import ua.goit.command.Command;
import ua.goit.model.entity.User;
import ua.goit.client.HttpClientUtil;
import ua.goit.model.util.UserUtil;
import ua.goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;

public class CreateUserWithArray implements Command {

    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public CreateUserWithArray(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        createUserWithArray();
    }

    @Override
    public String commandName() {
        return "user -create1";
    }

    public void createUserWithArray() {
        UserUtil userUtil = new UserUtil();
        List<User> users = userUtil.createListOfUsers();
        try {
            HttpResponse<String> responseOfCreate = httpClient.send(httpClientUtil.prepareCreateUserWithArrayRequest(users,
                    PetstoreHttpClient.getUserEndPoint() + PetstoreHttpClient.getUserWithArray()),
                    HttpResponse.BodyHandlers.ofString());
            if(responseOfCreate.statusCode() == 200) {
                System.out.println("Users were created successful \n" + responseOfCreate.body());
            }else{
                System.out.println(responseOfCreate.statusCode() + responseOfCreate.body());
            }
        }catch (IOException | InterruptedException io){
            io.printStackTrace();
        }
    }
}
