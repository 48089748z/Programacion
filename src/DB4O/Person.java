package DB4O;

/**
 * Created by 48089748z on 19/01/16.
 */
public class Person
{
    private String firstName;
    private String lastName;
    private int age;
    public Person() {}
    public Person(String firstName, String lastName, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public String getFirstName() { return firstName; }
    public void setFirstName(String value) { firstName = value; }
    public String getLastName() { return lastName; }
    public void setLastName(String value) { lastName = value; }
    public int getAge() { return age; }
    public void setAge(int value) { age = value; }

    public String toString() {return "\nPerson:\n    First Name: "+firstName+"\n    Last Name: "+lastName+"\n    Age: "+age;}

    public boolean equals(Object person)
    {
        Person otherPerson = (Person) person;
        if (otherPerson == this) {return true;}
        if (!(otherPerson instanceof Person)) {return false;}
        else {return false;}

       // return (this.firstName.equals(otherPerson.firstName) && this.lastName.equals(otherPerson.lastName) && this.age == otherPerson.age);
    }
}
