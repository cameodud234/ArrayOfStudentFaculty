public class InvalidGPA extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = -6492992811081843523L;

    InvalidGPA() {
        super("GPA must be 0-4.0...");
    }
}