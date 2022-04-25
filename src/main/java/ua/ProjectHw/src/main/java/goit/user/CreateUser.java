package goit.user;


import goit.client.HttpClientUtil;
import goit.client.PetstoreHttpClient;
import goit.entity.User;
import goit.util.UserUtil;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class CreateUser implements Command {
    private HttpClient httpClient;
    private HttpClientUtil httpClientUtil;

    public CreateUser() {
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        createUser();
    }

    @Override
    public String commandName() {
        return "user -create";
    }

    public void createUser() {
        UserUtil userUtil = new UserUtil();
        User user = userUtil.createUserThrowConsole();
        String endpoint = PetstoreHttpClient.getUserEndPoint();
        try {
            HttpResponse<String> responseOfCreate = httpClient.send(httpClientUtil.prepareCreateRequest(user, endpoint),
                    HttpResponse.BodyHandlers.ofString());
            if(responseOfCreate.statusCode() == 200) {
                System.out.println("The User was created successful \n" + responseOfCreate.body());
            }else{
                System.out.println(responseOfCreate.statusCode() + responseOfCreate.body());
            }
        }catch (IOException | InterruptedException io){
            io.printStackTrace();
        }
    }
}
