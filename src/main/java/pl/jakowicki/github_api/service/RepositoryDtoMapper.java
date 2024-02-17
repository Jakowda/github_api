package pl.jakowicki.github_api.service;


import org.springframework.stereotype.Component;
import pl.jakowicki.github_api.model.Branch;
import pl.jakowicki.github_api.model.dto.RepositoryDto;
import pl.jakowicki.github_api.model.Repository;

import java.util.List;

@Component
public class RepositoryDtoMapper {


    public static RepositoryDto mapToDto(Repository repository, List<Branch> branches){
        return new RepositoryDto(
                repository.getName(),
                repository.getOwner().getLogin(),
                branches);
    }
}
