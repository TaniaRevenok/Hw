package ua.goit.store;


import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetstoreHttpClient;
import ua.goit.user.Command;
import ua.goit.view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class DeleteOrder implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public DeleteOrder(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() throws FileNotFoundException {
        view.write("Enter order ID for the order should be deleted: ");
        String orderId = view.read();
        deleteOrder(orderId);
    }

    @Override
    public String commandName() {
        return "order -delete";
    }

    public void deleteOrder(String orderId) {
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareDeleteRequest(
                    PetstoreHttpClient.getStoreEndPoint() + PetstoreHttpClient.getOrder(), orderId), HttpResponse.BodyHandlers.ofString());
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

