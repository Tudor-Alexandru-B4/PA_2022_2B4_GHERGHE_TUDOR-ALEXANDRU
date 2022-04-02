package exceptions;

public class InvalidReportException extends Exception{

    public InvalidReportException(Exception e){
        super("Something with the report went wrong.", e);
    }
}
