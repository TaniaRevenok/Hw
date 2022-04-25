package ua.goit.store;


import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetstoreHttpClient;
import ua.goit.user.Command;
import ua.goit.view.View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class GetOrderById implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public GetOrderById(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() throws FileNotFoundException {
        view.write("Enter the order's id:");
        String id = view.read();
        getOrderById(id);
    }

    @Override
    public String commandName() {
        return "order -get";
    }

    public void getOrderById(String id) {
        try {
            HttpResponse<String> responseOfGet = httpClient.send(httpClientUtil.prepareGetRequest(
                            PetstoreHttpClient.getStoreEndPoint() + PetstoreHttpClient.getOrder(), id),
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
