import java.io.Serializable;

public class Faculty implements Person, Serializable, Comparable<Person>{

    /**
     *
     */
    private static final long serialVersionUID = -5325591841265357678L;
    private String department = null;
    private int ID = -1;
    private String fname = null;
    private String lname = null;
    private final int heirarchy = 100;  // created this to differentiate this class from Student

    // created this list for acceptable departments
    private final String[] currDepartment = {"ENGLISH","MATH","SCIENCE","ENGINEERING","MEDICINE","BUSINESS"};

    public Faculty(String fname, String lname, int ID, String department) {
        try{
            this.fname = fname;
            this.lname = lname;

            if(ID < 0){
                throw new InvalidID();
            }
            this.ID = ID;
            checkDept(department);
            this.department = department.toUpperCase();
        }
        catch(BadDepartmentName e){
            System.out.println(e.getMessage());
        }
        catch(InvalidID e){
            System.out.println(e.getMessage());
        }

    }

    public final String getFirstName(){
        return this.fname;
    }
    public final String getLastName() {
        return this.lname;
    }

    public final String getDepartment(){
        return this.department;
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
        this.lname + ", ID: " + this.ID + " , department: " + this.department;
    }

    private final void checkDept(String s) throws BadDepartmentName {
        int deptSize = this.currDepartment.length;
        boolean checker = false;
        for(int i = 0; i < deptSize; i++){
            if(this.currDepartment[i].compareToIgnoreCase(s) == 0){
                checker = true;
            }
        }
        if(checker == false){
            throw new BadDepartmentName();
        }
    }

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