package pl.jakowicki.github_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class Repository {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private boolean fork;
    @Getter
    @Setter
    private String branches_url;
    @Getter
    @Setter
    private Owner owner;



}
