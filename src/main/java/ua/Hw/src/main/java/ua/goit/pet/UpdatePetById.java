package ua.goit.pet;


import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetstoreHttpClient;
import ua.goit.user.Command;
import ua.goit.view.View;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class UpdatePetById implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public UpdatePetById(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }


    @Override
    public void process() {
        view.write("Enter id of pet, which should be updated");
        String id = view.read();
        view.write("Enter new name for pet " + id);
        String updatedName = view.read();
        view.write("Enter new status for pet " + id + " from: available, pending, sold ");
        String updatedStatus = view.read();
        updatePet(id, updatedName, updatedStatus);
    }

    @Override
    public String commandName() {
        return "pet -update1";
    }

    public void updatePet(String id, String updatedName, String updatedStatus) {
        String endpoint = PetstoreHttpClient.getPetEndPoint();
        try {
            HttpResponse<String> responseOfCreate = httpClient.send(httpClientUtil.prepareUpdateWithData(
                            endpoint,
                            id,
                            updatedName,
                            updatedStatus
                    ),
                    HttpResponse.BodyHandlers.ofString());
            if(responseOfCreate.statusCode() == 200) {
                System.out.println("The Pet " + id + " was successfully updated \n" + responseOfCreate.body());
            }else{
                System.out.println(responseOfCreate.statusCode() + responseOfCreate.body());
            }
        }catch (IOException | InterruptedException io){
            io.printStackTrace();
        }
    }
}
