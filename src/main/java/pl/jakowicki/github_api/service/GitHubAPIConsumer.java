package pl.jakowicki.github_api.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.jakowicki.github_api.exception.MyCustomException;
import pl.jakowicki.github_api.model.Branch;
import pl.jakowicki.github_api.exception.ErrorResponse;
import pl.jakowicki.github_api.model.Repository;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class GitHubAPIConsumer {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpClient httpClient = HttpClient.newHttpClient();

    public List<Repository> getRepositoryForUser(String username) throws URISyntaxException, IOException, InterruptedException, MyCustomException {
        //Find all repository by specific user
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.github.com/users/"+ username + "/repos"))
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> getResposne = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        if (getResposne.statusCode() == 404) {
            // User not exist, throw error
            throw new MyCustomException(new ErrorResponse(getResposne.statusCode(), "No user with such username found"));
        } else {
            return parseRepositoryListResponse(getResposne.body());
        }
    }

    private List<Repository> parseRepositoryListResponse(String responseBody) {
        //Parsing Json response to proper list of  Repository Objects
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Repository>>() {}.getType();
            return gson.fromJson(responseBody, listType);
    }


    public List<Branch> getBranchesList(Repository repository) throws URISyntaxException, IOException, InterruptedException, IllegalStateException {
        //Find all branches assigned to specyfic Repository
        String url = repository.getBranches_url();
        String buildUrl = url.substring(0, url.indexOf("{"));
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(buildUrl))
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> getResposne = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        return parseBranchListResponse(getResposne.body());
    }

    private List<Branch> parseBranchListResponse(String responseBody) {
        //Parsing Json response to proper list of  Branch Objects
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Branch>>() {}.getType();
        return gson.fromJson(responseBody, listType);
    }
}
