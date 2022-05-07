package ua.goit.command.store;

import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetstoreHttpClient;
import ua.goit.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class ReturnInventory implements Command {
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public ReturnInventory() {
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() throws FileNotFoundException {
        returnInventory();
    }

    @Override
    public String commandName() {
        return "inventory";
    }

    public void returnInventory() {
        String endpoint = PetstoreHttpClient.getStoreEndPoint() + PetstoreHttpClient.getPetInventory();
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareGetRequestWithoutData(endpoint),
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
