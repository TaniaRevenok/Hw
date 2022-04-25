package goit.client;

import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.*;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

public class HttpClientUtil<T> {
    Gson gson = new Gson();
    public HttpRequest prepareCreateRequest(T entity, String endpoint) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .uri(URI.create(PetstoreHttpClient.getURL() + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(entity)))
                .build();
    }

    public HttpRequest prepareGetRequest(String endpoint, String parameter) {
        return HttpRequest.newBuilder()
                .uri(URI.create(PetstoreHttpClient.getURL() + endpoint + "/" + parameter))
                .GET()
                .build();
    }

    public HttpRequest prepareUpdateWithData(String endpoint, String parametr, T entity) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .uri(URI.create(PetstoreHttpClient.getURL() + endpoint + "/" + parametr))
                .method("PUT", HttpRequest.BodyPublishers.ofString(gson.toJson(entity)))
                .build();
    }

    public HttpRequest prepareDeleteRequest(String endpoint, String parameter) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json")
                .uri(URI.create(PetstoreHttpClient.getURL() + endpoint + "/" + parameter))
                .DELETE()
                .build();
    }

    public HttpRequest prepareCreateUserWithArrayRequest(List<T> entities, String endpoint) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .uri(URI.create(PetstoreHttpClient.getURL() + endpoint))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(entities)))
                .build();
    }

    public HttpRequest prepareLoginUserRequest(String login, String password) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .uri(URI.create(PetstoreHttpClient.getURL() + PetstoreHttpClient.getUserEndPoint() +
                        "/login?username=" + "&password=" + password))
                .GET()
                .build();
    }

    public HttpRequest prepareGetRequestWithoutData(String endpoint) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/json; charset=UTF-8")
                .uri(URI.create(PetstoreHttpClient.getURL() + endpoint))
                .GET()
                .build();
    }

    public HttpRequest prepareUpdateWithData(String endpoint, String id, String newName, String updatedStatus) {
        return HttpRequest.newBuilder()
                .header("Content-type", "application/x-www-form-urlencoded")
                .uri(URI.create(PetstoreHttpClient.getURL() + endpoint + "/" + id))
                .POST(HttpRequest.BodyPublishers.ofString("name=" + newName + "&status=" + updatedStatus))
                .build();
    }

    public static HttpPost prepareUploadAnImageForPet(String endpoint, String additionalMetaData, String filePath) throws FileNotFoundException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        HttpPost uploadFileRequest = new HttpPost(URI.create(PetstoreHttpClient.getURL() + endpoint));
        File file = new File(filePath);
        builder.addTextBody("additionalMetadata:", additionalMetaData);
        builder.addBinaryBody("file",
                file,
                ContentType.MULTIPART_FORM_DATA,
                file.getName());
        uploadFileRequest.setEntity(builder.build());
        return uploadFileRequest;
    }
}

