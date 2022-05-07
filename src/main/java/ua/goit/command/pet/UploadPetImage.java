package ua.goit.command.pet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import ua.goit.client.HttpClientUtil;
import ua.goit.client.PetstoreHttpClient;
import ua.goit.command.Command;
import ua.goit.view.View;

import java.io.*;
import java.net.http.HttpClient;

public class UploadPetImage implements Command {
    View view;
    HttpClient httpClient;
    HttpClientUtil httpClientUtil;

    public UploadPetImage(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        httpClientUtil = new HttpClientUtil();
    }

    @Override
    public void process() throws FileNotFoundException {
        view.write("Enter id of pet, which should be updated");
        String id = view.read();
        view.write("Enter tag:");
        String tag = view.read();
        view.write("Enter image path:");
        String imagePath = view.read();
        uploadImage(id, tag, imagePath);
    }

    @Override
    public String commandName() {
        return "pet -img-upload";
    }

    public void uploadImage(String id, String additionalData, String imagePath) {
        CloseableHttpClient DEFAULT_CLIENT = HttpClients.createDefault();
        String endpoint = PetstoreHttpClient.getPetEndPoint() + "/" + id + "/uploadImage";
        try {
            HttpResponse response = DEFAULT_CLIENT.execute(HttpClientUtil.prepareUploadAnImageForPet(endpoint,
                    additionalData, imagePath));
            HttpEntity responseEntity = response.getEntity();
            String sResponse = EntityUtils.toString(responseEntity, "UTF-8");
            System.out.println(sResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
