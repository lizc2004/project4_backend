package noemicoppotelli.project4_backend.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException perId(String entita, Long id) {
        return new NotFoundException(entita + " con id " + id + " non trovato/a");
    }
}
