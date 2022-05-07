package ua.goit.command.store;

import com.google.gson.Gson;
import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetstoreHttpClient;
import ua.goit.command.Command;
import ua.goit.model.entity.Order;
import ua.goit.model.util.OrderUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class PlaceOrder implements Command {
    private HttpClient httpClient;
    private HttpClientUtil httpClientUtil;

    public PlaceOrder() {
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() throws FileNotFoundException {
        OrderUtil orderUtil = new OrderUtil();
        Order order = orderUtil.createOrderThrowConsole();
        createOrder(order);
    }

    @Override
    public String commandName() {
        return "order -create";
    }

    public void createOrder(Order order) {
        String endpoint = PetstoreHttpClient.getStoreEndPoint() + PetstoreHttpClient.getOrder();
        try {
            HttpResponse<String> responseOfCreate = httpClient.send(httpClientUtil.prepareCreateRequest(order, endpoint),
                    HttpResponse.BodyHandlers.ofString());
            if(responseOfCreate.statusCode() == 200) {
                System.out.println("The Order was created successful \n" + responseOfCreate.body());
            }else{
                System.out.println(responseOfCreate.statusCode() + responseOfCreate.body());
            }
        }catch (IOException | InterruptedException io){
            io.printStackTrace();
        }
    }
}
