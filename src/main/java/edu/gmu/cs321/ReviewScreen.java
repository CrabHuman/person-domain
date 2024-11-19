package edu.gmu.cs321;

import java.lang.*;
import java.util.Date;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ReviewScreen extends Screen {

    private static String QUERY = """
    SELECT formID, immigrantID, firstName, lastName, dateOfBirth, address, phoneNumber, email, dependentID, 
    DPfirstName, DPlastName, DPdateOfBirth, DPaddress, DPphoneNumber, DPemail FROM DependentForm WHERE formID = 
    """;
    
    @FXML
    private TextField fxParentFirstName;
    @FXML
    private TextField fxParentLastName;
    @FXML
    private TextField fxParentID;
    @FXML
    private TextField fxParentDateOfBirth;
    @FXML
    private TextField fxParentAddress;
    @FXML
    private TextField fxParentPhoneNumber;
    @FXML
    private TextField fxParentEmail;

    @FXML
    private TextField fxDependentFirstName;
    @FXML
    private TextField fxDependentLastName;
    @FXML
    private TextField fxDependentID;
    @FXML
    private TextField fxDependentDateOfBirth;
    @FXML
    private TextField fxDependentAddress;
    @FXML
    private TextField fxDependentPhoneNumber;
    @FXML
    private TextField fxDependentEmail;
    @FXML
    private TextField fxDependentParentID;

    @FXML
    private void saveForm() throws IOException {
        // save the form to the database
        
    }
    
    @FXML
    private void submitForm() throws IOException {
        saveForm();
        App.workflow.AddWFItem(form.getID(), "Approve");
        unloadForm();
    }

    @FXML
    private void backToPrimary() throws IOException {
        // save form to database
        saveForm();
        // go back to previous page
        App.setRoot("primary");
    }

    @FXML
    private void validate() throws IOException {
        
    }
    
    @FXML
    private void loadNextForm() throws IOException {
        // save form to database
        saveForm();
        // unload this form
        unloadForm();
        // retrieve new form from the database
        int nextID = App.workflow.GetNextWFItem("Approve");
        
        if(nextID >= 0){
            
            try {
                conn = DriverManager.getConnection(App.DB_URL, App.USER, App.PASS);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(QUERY + nextID);
                rs.next();
                form = new DependentForm(new Immigrant(), new Dependent(), -1);


                form.setID(rs.getInt("formID"));
                //dateOfBirth, address, phoneNumber, email, dependentID, 
                //DPfirstName, DPlastName, DPdateOfBirth, DPaddress, DPphoneNumber, DPemail
                form.getParent().setPersonID(rs.getInt("immigrantID"));
                form.getParent().setFirstName(rs.getString("firstname"));
                form.getParent().setLastName(rs.getString("lastname"));
                form.getParent().setDateOfBirth(new Date(rs.getLong("dateOfBirth")));
                form.getParent().setAddress(rs.getString("address"));
                form.getParent().setPhoneNumber(rs.getLong("phoneNumber"));
                form.getParent().setEmail(rs.getString("email"));
    
                form.getDependent().setParent(form.getParent());
                form.getDependent().setPersonID(rs.getInt("dependentID"));
                form.getDependent().setFirstName(rs.getString("DPfirstname"));
                form.getDependent().setLastName(rs.getString("DPlastname"));
                form.getDependent().setDateOfBirth(new Date(rs.getLong("DPdateOfBirth")));
                form.getDependent().setAddress(rs.getString("DPaddress"));
                form.getDependent().setPhoneNumber(rs.getLong("DPphoneNumber"));
                form.getDependent().setEmail(rs.getString("DPemail"));



            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("OOPS");
            return;
        }
        //super.form = null;
        



        
        fxParentFirstName.setText(super.form.getParent().getFirstName());
        fxParentLastName.setText(super.form.getParent().getLastName());
        fxParentID.setText(String.valueOf(super.form.getParent().getID()));
        fxParentDateOfBirth.setText(super.form.getParent().getDateOfBirth().toString());
        fxParentAddress.setText(super.form.getParent().getFirstName());
        fxParentPhoneNumber.setText(String.valueOf(super.form.getParent().getPhoneNumber()));
        fxParentEmail.setText(super.form.getParent().getEmail());

        fxDependentFirstName.setText(super.form.getDependent().getFirstName());
        fxDependentLastName.setText(super.form.getDependent().getLastName());
        fxDependentID.setText(String.valueOf(super.form.getDependent().getID()));
        fxDependentDateOfBirth.setText(super.form.getDependent().getDateOfBirth().toString());
        fxDependentAddress.setText(super.form.getDependent().getFirstName());
        fxDependentPhoneNumber.setText(String.valueOf(super.form.getDependent().getPhoneNumber()));
        fxDependentEmail.setText(super.form.getDependent().getEmail());
        fxDependentParentID.setText(String.valueOf(super.form.getDependent().getParent().getID()));        
    }

    @FXML
    public void unloadForm() throws IOException {
        fxParentFirstName.setText("");
        fxParentLastName.setText("");
        fxParentID.setText("");
        fxParentDateOfBirth.setText("");
        fxParentAddress.setText("");
        fxParentPhoneNumber.setText("");
        fxParentEmail.setText("");

        fxDependentFirstName.setText("");
        fxDependentLastName.setText("");
        fxDependentID.setText("");
        fxDependentDateOfBirth.setText("");
        fxDependentAddress.setText("");
        fxDependentPhoneNumber.setText("");
        fxDependentEmail.setText("");
        fxDependentParentID.setText("");
        super.form = null;
    }
}
