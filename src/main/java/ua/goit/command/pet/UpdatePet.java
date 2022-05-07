package ua.goit.command.pet;

import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetstoreHttpClient;
import ua.goit.command.Command;
import ua.goit.model.entity.Pet;
import ua.goit.model.util.PetUtil;
import ua.goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class UpdatePet implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public UpdatePet(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() {
        PetUtil petUtil = new PetUtil();
        Pet pet = petUtil.createPetThrowConsole();
        updatePet(pet);
    }

    @Override
    public String commandName() {
        return "pet -update";
    }

    public void updatePet(Pet pet) {
        String endpoint = PetstoreHttpClient.getPetEndPoint();
        String parameter = "";
        try {
            HttpResponse<String> responseOfCreate = httpClient.send(httpClientUtil.prepareUpdateWithData(
                    endpoint,
                    parameter,
                    pet),
                    HttpResponse.BodyHandlers.ofString());
            if(responseOfCreate.statusCode() == 200) {
                System.out.println("The Pet was successfully updated \n" + responseOfCreate.body());
            }else{
                System.out.println(responseOfCreate.statusCode() + responseOfCreate.body());
            }
        }catch (IOException | InterruptedException io){
            io.printStackTrace();
        }
    }
}
