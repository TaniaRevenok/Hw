package ua.goit.command.pet;

import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetstoreHttpClient;
import ua.goit.command.Command;
import ua.goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class DeletePet implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public DeletePet(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        view.write("Enter Pet's id:");
        String id = view.read();
        deletePet(id);
    }

    @Override
    public String commandName() {
        return "pet -delete";
    }

    public void deletePet(String id) {
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareDeleteRequest(
                    PetstoreHttpClient.getPetEndPoint(), id), HttpResponse.BodyHandlers.ofString());
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
