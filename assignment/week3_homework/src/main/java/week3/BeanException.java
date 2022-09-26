package week3;

public class BeanException extends Exception{
    public enum ErrorType {FILE_NOT_EXITS,METHOD_ERROR,PROP_READ_ERROR, TAG_NOT_EXISTS, PROPERTY_NOT_EXISTS, CLASS_NOT_FOUND, NO_SUCH_METHOD, INSTANTIATION_FAIL, INVOCATION_EXCEPTION, NO_LEGAL_ACCESS}
    private ErrorType errorType;

    public BeanException(ErrorType errorType, String message){
        super(message);
        this.errorType =errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
