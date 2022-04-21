package ua.goit.command.pet;

import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetStoreHttpClient;
import ua.goit.command.Command;
import ua.goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class GetPetById implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public GetPetById(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        view.write("Enter Id:");
        String id = view.read();
        getUserById(id);
    }

    @Override
    public String commandName() {
        return "pet -get";
    }

    public void getUserById(String id) {
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareGetRequest(
                    PetStoreHttpClient.getPetEndPoint(), id), HttpResponse.BodyHandlers.ofString());
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

