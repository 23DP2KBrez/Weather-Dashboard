package lv.rvt;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;

class Response
{
    public boolean status;
    public String body;
}

public class RequestManager 
{
    public static Response createSimpleRequest(String url)
    {
        Response response = new Response();
        response.status = false;

        if(url == null) return response;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
        try
        {
            HttpResponse<String> respo = client.send(request, BodyHandlers.ofString());
            response.body = respo.body();

        }
        catch (IOException | InterruptedException e) 
        {
            e.printStackTrace();
        }
        response.status = true;
        return response;
    }
}
