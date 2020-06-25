import java.io.Serializable;

public class Student implements Person, Serializable,Comparable<Person>{

    /**
     *
     */
    private static final long serialVersionUID = 177904030306704420L;
    private double GPA = -1;
    private int ID = -1;
    private String fname = null;
    private String lname = null;
    private final int heirarchy = 50;  // Created this to differentiate this class from Faculty

    public Student(String fname, String lname, int ID, double GPA) {

        try{

            this.fname  = fname;
            this.lname = lname;
            if(ID < 0){ throw new InvalidID(); }
            this.ID = ID;
            if(GPA < 0 || GPA > 4.0){ throw new InvalidGPA(); }
            this.GPA = GPA;

        }
        catch(InvalidGPA e){
            System.out.println(e.getMessage());
        }
        catch(InvalidID e){
            System.out.println(e.getMessage());
        }

    }

    public final String getFirstName() {
        return this.fname;
    }

    public final String getLastName(){
        return this.lname;
    }

    public final double getGPA(){
        return this.GPA;
    }

    public final int getID() {
        return this.ID;
    }

    public final int getHeirarchy() {
        return this.heirarchy;
    }

    public void setFirstName(String fname) {
        this.fname = fname;
    }

    public void setLastName(String lname){
        this.lname = lname;
    }

    public void setID(int ID) throws InvalidID {
        if(ID < 0){
            throw new InvalidID();
        }
        this.ID = ID;
    }

    public final String toString(){
        return "firstname: " + this.fname + " , lastname: " + 
        this.lname+ ", ID: " + this.ID + " , GPA: " + this.GPA;
    }

    // Overrided compareTo function to compare objects for sorting
    public final int compareTo( Person o) {
        if(this.heirarchy == o.getHeirarchy()){

            if(this.lname.compareToIgnoreCase(o.getLastName()) == 0) {
                return 0;
            }
            else if(this.lname.compareToIgnoreCase(o.getLastName()) > 0){
                return 1;
            }
            else if(this.lname.compareToIgnoreCase(o.getLastName()) < 0){
                return -1;
            }

        }

        else if(this.heirarchy > o.getHeirarchy()){
            return 1;
        }
        else if(this.heirarchy < o.getHeirarchy()){
            return -1;
        }

        return 0;
    } 

}