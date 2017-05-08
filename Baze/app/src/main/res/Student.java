/**
 * Created by student on 8.5.2017.
 */

public class Student {

    private String firstName;
    private String lastName;
    private String IndexNumber;

    public Student(String firstName, String lastName, String indexNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        IndexNumber = indexNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIndexNumber() {
        return IndexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        IndexNumber = indexNumber;
    }
}
