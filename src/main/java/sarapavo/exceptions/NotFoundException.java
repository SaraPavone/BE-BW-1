package sarapavo.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("L'elemento con ID " + id + " non è stato trovato");
    }
}
