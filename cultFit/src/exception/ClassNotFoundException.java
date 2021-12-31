package exception;

public class ClassNotFoundException extends Exception {
    private final String message;

    public ClassNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

