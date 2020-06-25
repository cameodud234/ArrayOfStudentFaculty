public class InvalidID extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = -649299281108143523L;

    InvalidID() {
        super("ID must be a positive integer...");
    }
}