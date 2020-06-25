import java.util.*;
import java.io.*; 

public class College implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 4961460078959767033L;
    private final int sizeLimit = 100; // max size of database
    private int peopleSize = 0;  // This is the current size at runtime of populated array
    private String collegeName = null;  // denotes the College of instance object
    private Person[] people = new Person[sizeLimit];

    public College(){
        // default construct
    }

    public College(String s){
        this.collegeName = s;
    }

    // To make it easier to see College contents
    public String toString(){
        String elems = "";
        for(int i = 0; i < this.peopleSize; i++){
            elems += this.people[i] + "\n";
        }
        return elems;
    }

    /*****************************************/
    ////
    // Created 2 addPerson functions
    // each for the 2 types Students and Faculty

    public void addPerson(Student s) throws ArraysTooManyPeople, InvalidGPA, InvalidID {

        if(this.peopleSize >= this.sizeLimit){ 
            throw new ArraysTooManyPeople("Size of Person array must be less than " + this.sizeLimit); 
        }

        for(int i = 0; i < this.sizeLimit; i++){
            if(this.people[i] == null){
                this.people[i] = new Student(s.getFirstName(), s.getLastName(), s.getID(), s.getGPA());
                this.peopleSize++;
                break;
            }
        }
        
    }

    public void addPerson(Faculty s) throws ArraysTooManyPeople, BadDepartmentName, InvalidID {

        if(this.peopleSize >= this.sizeLimit){ 
            throw new ArraysTooManyPeople("Size of Person array must be less than " + this.sizeLimit); 
        }

        for(int i = 0; i < this.sizeLimit; i++){
            if(this.people[i] == null){
                this.people[i] = new Faculty(s.getFirstName(), s.getLastName(), s.getID(), s.getDepartment());
                this.peopleSize++;
                break;
            }
        }

    }

    /*****************************************/


    public void setName(String s){
        this.collegeName = s;
    }

    public final String getName(){
        return this.collegeName;
    }

    public void storeCollege(String filename) throws IOException {

        FileOutputStream fOut = null;

        ObjectOutputStream ops = null;

        try {

            fOut = new FileOutputStream(filename);
            ops = new ObjectOutputStream(fOut);

            System.out.println("College data is beginning to stream into file " + filename + "...\n");

            ops.writeObject(this);

            System.out.println("File write success to file " + filename + "...");

        } 
        catch(IOException e){
            System.out.println("IOException in StoreCollege caught...");
        }
        catch (Exception e) {
            System.out.println("Something else went wrong in storeCollege, exiting the program");
            System.exit(0);
        }
        finally{
            ops.close();
            fOut.close();
        }

    }

    public static College loadCollege(String filename) throws IOException, ClassNotFoundException {

        FileInputStream fIn = null;  // file object

        ObjectInputStream ips = null;  // open bin file for streaming

        College anotherCollege = null;

        try{

            anotherCollege = new College();

            fIn = new FileInputStream(filename);
            ips = new ObjectInputStream(fIn);
        
            anotherCollege = (College)ips.readObject();

            System.out.println("File read success from " + filename + "...");

        }
        /* catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } */
        catch(ClassNotFoundException e){
            System.out.println("Class not found...");
        }
        catch(FileNotFoundException e){
            System.out.println("File not found...");
            System.exit(0);
        }
        catch(IOException e){
            System.out.println("IOException in loadCollege caught...");
        }
        catch(Exception e){
            System.out.println("Something else went wrong in load College, closing the program...");
            System.exit(0);
        }
        finally{
            ips.close();
            fIn.close();
        }

        return anotherCollege;

    }


    // Checks if 2 different instantiated College's have
    // the same data
    public final boolean isSame(College college){
        
        boolean checker = true;


        if(this.peopleSize != college.peopleSize){
            checker = false;
            return checker;
        }

        // I sorted the arrays just in case they were not
        // sorted to ensure this function runs correctly
        Arrays.sort(this.people, 0, this.peopleSize);
        Arrays.sort(college.people, 0, college.peopleSize);

        for( int i = 0; i < this.peopleSize; i++){

            boolean fNameCompare = this.people[i].getFirstName().compareTo(college.people[i].getFirstName()) == 0;
            boolean lNameCompare = this.people[i].getLastName().compareTo(college.people[i].getLastName()) == 0;
            boolean IDCompare = this.people[i].getID() == college.people[i].getID();
        
            if(this.people[i].getHeirarchy() == 50 && college.people[i].getHeirarchy() == 50){
            
                boolean gpaCompare = ((Student)this.people[i]).getGPA() == ((Student)college.people[i]).getGPA();

                if(fNameCompare && lNameCompare && IDCompare && gpaCompare){
                    continue;
                }
                else{
                    checker = false;
                    break;
                }

            }
            else if (this.people[i].getHeirarchy() == 100 && college.people[i].getHeirarchy() == 100){

                // Separated this to make the casting more legible
                String thisElemDep = ((Faculty)this.people[i]).getDepartment();
                String collegeElemDep = ((Faculty)college.people[i]).getDepartment();
                Boolean deptCompare = thisElemDep.compareTo(collegeElemDep) == 0;

                if(fNameCompare && lNameCompare && IDCompare && deptCompare){
                    continue;
                }
                else{
                    checker = false;
                    break;
                }

            }
            else if(this.people[i].getHeirarchy() != college.people[i].getHeirarchy()){
                checker = false;
                break;
            }

        }

        return checker;
        
    }

        
    public static void main(String[] args){
        try{

            final String file = "myFile.bin";

            College college = new College("City College");
            Student student1 = new Student("Cameron", "EDudley", 126354, 4.0);
            Student student2 = new Student("Cam", "TDudley", 541894, 2.6);
            Faculty faculty2 = new Faculty("Kelly", "Price", 874875, "english");
            Student student3 = new Student("Ron", "ADudley", 874895,3.5);
            Faculty faculty4 = new Faculty("Tom", "Cruise", 564655, "Math");
            Student student4 = new Student("Meron", "BDudley",561564, 3.7);
            Student student5 = new Student("Eron", "MDudley", 8654968,2.65);
            Faculty faculty1 = new Faculty("Bob", "Dylan", 5646545, "Business");
            college.addPerson(student1);
            college.addPerson(student2);
            college.addPerson(faculty2);
            college.addPerson(student3);
            college.addPerson(student4);
            college.addPerson(faculty4);
            college.addPerson(student5);
            college.addPerson(faculty1);

            System.out.println("\n--------------------\n");

            Arrays.sort(college.people, 0, college.peopleSize);

            college.storeCollege(file);
            
            System.out.println("\n--------------------\n");

            College anotherCollege = new College();

            anotherCollege = loadCollege(file);
            
            //System.out.println(anotherCollege);

            if(anotherCollege.isSame(college)){
                System.out.println("Objects are the same...");
            }
            else{
                System.out.println("Objects are different...");
            }

        }
        catch(ArraysTooManyPeople e){
            System.out.println(e.getMessage());
        }
        /* catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } */
        catch(BadDepartmentName e){
            System.out.println(e.getMessage());
        }
        catch(InvalidID e){
            System.out.println(e.getMessage());
        }
        catch(InvalidGPA e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println("Exception caught...");
            System.exit(0);
        }
        finally{
            System.out.println("System will now shutdown...");
        }
    }
}