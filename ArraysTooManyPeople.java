public class ArraysTooManyPeople extends Exception{
    
    /**
     *
     */
    private static final long serialVersionUID = 3798696525889586835L;
    private String s = "You must have less than the specified amount people per array...";

    public ArraysTooManyPeople(){
        super("You must have less than the specified amount of people per array...");
    }
    public ArraysTooManyPeople(String s){
        super(s);
        this.s = s;
    }    
}