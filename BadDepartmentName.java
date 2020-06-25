public class BadDepartmentName extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public BadDepartmentName(){
        super("Faculty must be in an existing department");
    }
    public BadDepartmentName(String s){
        super(s);
    }
}