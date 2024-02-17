package pl.jakowicki.github_api.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Branch {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Commit commit;


}
