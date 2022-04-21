package ua.goit.command.pet;

import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetStoreHttpClient;
import ua.goit.command.Command;
import ua.goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class GetPetByStatus implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public GetPetByStatus(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        view.write("Enter status from the list: available, pending, sold");
        String status = view.read();
        getPetsByStatus(status);
    }

    @Override
    public String commandName() {
        return "pet -get1";
    }

    public void getPetsByStatus(String status) {
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareGetRequest(
                    PetStoreHttpClient.getPetEndPoint() + PetStoreHttpClient.getPetsByStatus(),
                    "?status=" + status), HttpResponse.BodyHandlers.ofString());
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
