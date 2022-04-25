package goit.util;


import goit.entity.Order;
import goit.view.View;

import java.util.Locale;

public class OrderUtil {
    public Order createOrderThrowConsole() {
        View view = new View();
        Order order = new Order();
        view.write("Please enter quantity");
        order.setQuantity(Integer.parseInt(view.read()));
        view.write("Please enter the date in format dd-mm-yyyy");
/*        int[] dateArray = Arrays.stream(view.read().split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();*/
        order.setShipDate(view.read());
        view.write("Please enter status from: placed, approved, delivered ");
        order.setStatus(view.read());
        view.write("Is order completed? Y/N ?");
        boolean isComplete = view.read().toUpperCase(Locale.ROOT).equals("Y");
        order.setComplete(isComplete);
        return order;
    }
}

