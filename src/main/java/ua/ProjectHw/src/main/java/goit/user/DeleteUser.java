package goit.user;


import goit.client.HttpClientUtil;
import goit.client.PetstoreHttpClient;
import goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class DeleteUser implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public DeleteUser(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        view.write("Enter username for the user should be deleted: ");
        String username = view.read();
        deleteUser(username);
    }

    @Override
    public String commandName() {
        return "user -delete";
    }

    private void deleteUser(String username) {
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareDeleteRequest(
                    PetstoreHttpClient.getUserEndPoint(), username), HttpResponse.BodyHandlers.ofString());
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

