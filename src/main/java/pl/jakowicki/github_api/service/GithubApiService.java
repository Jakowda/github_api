package pl.jakowicki.github_api.service;

import org.springframework.stereotype.Service;
import pl.jakowicki.github_api.exception.MyCustomException;
import pl.jakowicki.github_api.model.Repository;
import pl.jakowicki.github_api.model.dto.RepositoryDto;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GithubApiService {

    private GitHubAPIConsumer gitHubConsumer;

    public GithubApiService(GitHubAPIConsumer gitHubConsumer) {
        this.gitHubConsumer = gitHubConsumer;
    }

    public Optional<List<RepositoryDto>> getUsersRepositoryData(String username) throws URISyntaxException, IOException, InterruptedException, MyCustomException {
        //Find all repositories and map them with branches data
        List<Repository> repositoryList = gitHubConsumer.getRepositoryForUser(username);
        repositoryList.removeIf(repository -> repository.isFork()); //Delete forks repositories
        return Optional.of(mapRepositoriesWithBranches(repositoryList));
    }

    private List<RepositoryDto> mapRepositoriesWithBranches(List<Repository> repositoryList){
        List<RepositoryDto> repositoryDtos = new ArrayList<>();
        repositoryList.forEach(repository -> {
            //Mapping all Repositories to RepositoriesDtos with Branches
            try {
                repositoryDtos.add(RepositoryDtoMapper.mapToDto(repository, gitHubConsumer.getBranchesList(repository)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return repositoryDtos;
    }
}
