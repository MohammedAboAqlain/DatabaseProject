package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteCouncil implements Initializable {

    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public ComboBox comboBox;
    @FXML public Label statusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(FXCollections.observableArrayList(db.getCouncils()));
    }


    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Council);
    }

    public void onConfirmBtn(ActionEvent event) {
        if(db.DeleteCouncil(comboBox.getValue().toString()) == 1){
            statusLabel.setText("Deleted Successfully...");
        }else {
            statusLabel.setText("There is no delete occur...");
        }
    }


}
