public interface Person{
    String getFirstName();
    String getLastName();
    int getID();
    void setFirstName(String s);
    void setLastName(String s);
    void setID(int s) throws InvalidID;
    int getHeirarchy();
    int compareTo(Person o);
}