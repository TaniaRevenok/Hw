package goit.store;


import goit.client.HttpClientUtil;
import goit.client.PetstoreHttpClient;
import goit.entity.Order;
import goit.user.Command;
import goit.util.OrderUtil;

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
