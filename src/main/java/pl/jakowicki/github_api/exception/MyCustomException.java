package pl.jakowicki.github_api.exception;
public class MyCustomException extends Exception {

    private ErrorResponse errorResponse;

    public MyCustomException(ErrorResponse errorResponse){
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}