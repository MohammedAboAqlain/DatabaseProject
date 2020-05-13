package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateCouncil implements Initializable {



    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();
    private String old_councilName;

    @FXML public AnchorPane rootPane;
    @FXML public ComboBox comboBox;
    @FXML public TextField CouncilName;
    @FXML public TextField ShaikhName;
    @FXML public TextField Book;
    @FXML public TextField Subject;
    @FXML public TextField Mosque;
    @FXML public Label statusLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(FXCollections.observableArrayList(db.getCouncils()));
    }


    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Council);
    }

    public void onComboboxAction(ActionEvent event) {
        // initialize the textFields
        ArrayList<String> councilFields = db.getCouncilFields(comboBox.getValue().toString());
        CouncilName.setText(councilFields.get(0));
        Subject.setText(councilFields.get(1));
        Book.setText(councilFields.get(2));
        ShaikhName.setText(councilFields.get(3));
        Mosque.setText(councilFields.get(4));
        old_councilName = CouncilName.getText();
    }

    public void onConfirmBtn(ActionEvent event) {
        if(db.UpdateCouncil(old_councilName, CouncilName.getText(), Subject.getText(), Book.getText(),
                ShaikhName.getText(), Mosque.getText()) == 1){
            statusLabel.setText("Updated Successfully...");
        }else {
            statusLabel.setText("There is no update occur...");
        }
    }
}
