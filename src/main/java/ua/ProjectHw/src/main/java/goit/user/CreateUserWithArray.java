package goit.user;


import goit.client.HttpClientUtil;
import goit.client.PetstoreHttpClient;
import goit.entity.User;
import goit.util.UserUtil;
import goit.view.View;

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