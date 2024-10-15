package sarapavo.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("L'evento con ID " + id + " non è stato trovato");
    }
}
