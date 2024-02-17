package pl.jakowicki.github_api.controller;


import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jakowicki.github_api.exception.MyCustomException;

import pl.jakowicki.github_api.model.dto.RepositoryDto;
import pl.jakowicki.github_api.service.GithubApiService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/")
public class GitController {
    private final GithubApiService githubApiService;
    public GitController(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }
    @GetMapping("{username}")
    public ResponseEntity<Object> getData(@PathVariable String username) throws URISyntaxException, IOException, InterruptedException {
        Optional<List<RepositoryDto>> usersRepositoryData = null;
        try{
            usersRepositoryData = githubApiService.getUsersRepositoryData(username);
        } catch (MyCustomException e) {
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(e.getErrorResponse());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonResponse);
        }
            return ResponseEntity.status(HttpStatus.OK).body(usersRepositoryData.get());

    }
}
