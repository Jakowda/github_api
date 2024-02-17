package pl.jakowicki.github_api.model.dto;

import pl.jakowicki.github_api.model.Branch;

import java.util.List;


public class RepositoryDto {

    private String repositoryName;
    private String ownersLogin;
    private List<Branch> branchList;

    public RepositoryDto(String repositoryName, String ownersLogin, List<Branch> branchList) {
        this.repositoryName = repositoryName;
        this.ownersLogin = ownersLogin;
        this.branchList = branchList;
    }


    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnersLogin() {
        return ownersLogin;
    }

    public void setOwnersLogin(String ownersLogin) {
        this.ownersLogin = ownersLogin;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }
}
