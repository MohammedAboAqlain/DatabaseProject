package Controller;

import Models.DBModel;
import Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CreateCouncil {

    Navigation nav = new Navigation();
    DBModel db = DBModel.getModel();

    @FXML public AnchorPane rootPane;
    @FXML public TextField CouncilName;
    @FXML public TextField ShaikhName;
    @FXML public TextField Book;
    @FXML public TextField Subject;
    @FXML public TextField Mosque;
    @FXML public Label statusLabel;


    public void onBackBtn(ActionEvent event) {
        nav.navTo(rootPane, nav.Council);
    }

    public void onConfirmBtn(ActionEvent event) {
        if(CouncilName.getText() != null && ShaikhName.getText() != null
                && Book.getText() != null && Subject.getText() != null && Mosque.getText() != null){
            if(db.createCouncil(CouncilName.getText(), Subject.getText(),
                    Book.getText(), ShaikhName.getText(), Mosque.getText()) == 1){
                statusLabel.setText("Inserted Successfully...");
            }else {
                statusLabel.setText("There is no insert occur...");
            }
        }
    }
}
