package edu.gmu.cs321;
import java.util.Date;

public class Dependent extends Immigrant{
// private Immigrant parent;

    public Dependent(String firstName, String lastName, int personID, Date dateOfBirth, Immigrant parent) {
        super(firstName, lastName, personID, dateOfBirth);
        // this.parent=parent;
    }
    public Immigrant getParent(){
        return null;
        // return parent;
    }
    public static Dependent newDependent(String firstName, String lastName, int personID,Date dateOfBirth, Immigrant parent ){
        return null;
        // return new Dependent(firstName, lastName, personID, dateOfBirth,parent);

    }

}