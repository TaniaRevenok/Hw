package goit.user;


import goit.client.HttpClientUtil;
import goit.client.PetstoreHttpClient;
import goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class GetUserByUserName implements Command {

    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public GetUserByUserName(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        view.write("Enter the username:");
        String username = view.read();
        getUserByUserName(username);
    }

    @Override
    public String commandName() {
        return "user -get1";
    }

    public void getUserByUserName(String name) {
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareGetRequest(
                    PetstoreHttpClient.getUserEndPoint(), name), HttpResponse.BodyHandlers.ofString());
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
