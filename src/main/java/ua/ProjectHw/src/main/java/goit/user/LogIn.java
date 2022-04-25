package goit.user;

import goit.client.HttpClientUtil;
import goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class LogIn implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public LogIn(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        view.write("Please enter login:");
        String login = view.read();
        view.write("Please enter password:");
        String password = view.read();
        logIn(login, password);
    }

    @Override
    public String commandName() {
        return "user -login";
    }

    public void logIn(String login, String password) {
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareLoginUserRequest(login, password),
                    HttpResponse.BodyHandlers.ofString());
            if(responseOfGet.statusCode() == 200) {
                System.out.println(responseOfGet.body());
            }else{
                System.out.println(responseOfGet.statusCode() + responseOfGet.body());
            }
        }catch (IOException | InterruptedException io){
            io.printStackTrace();
        }
    }
}

