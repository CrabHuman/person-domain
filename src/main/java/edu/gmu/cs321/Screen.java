package edu.gmu.cs321;

import java.io.IOException;
import javafx.fxml.FXML;

public class Screen {
    private int screenID;
    private DependentForm form;

    public Screen(){
        screenID = -1;
        form = null;
    }

    public int getID(){
        return screenID;
    }
    public DependentForm getDependentForm(){
        return form;
    }

    public void setID(int screenID){
        this.screenID = screenID;
    }
    public void setDependentForm(DependentForm form){
        this.form = form;
    }

}