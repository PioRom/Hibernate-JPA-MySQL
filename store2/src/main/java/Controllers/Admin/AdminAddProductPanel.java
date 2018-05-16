package Controllers.Admin;

import Models.VerifyNewProduct;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AdminAddProductPanel {

    @FXML
    private Button addPictureButton;

    @FXML
    private TextField inputQuantity;

    @FXML
    private Text photoMessage;

    @FXML
    private TextField inputCategory;

    @FXML
    private Button addProductButton;

    @FXML
    private Text message;

    @FXML
    private TextField inputPrice;

    @FXML
    private TextField inputName;

    private Window mainStage;
    private File photoFile = null;
    private FileInputStream fileInputStream;

    @FXML
    void clickAddPictureButton(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        photoFile = fileChooser.showOpenDialog(mainStage);
        fileInputStream = new FileInputStream(photoFile);
        if (photoFile != null) {
            photoMessage.setText("Selected: " + photoFile.getName());
        }

    }

    @FXML
    void clickAddProductButton(ActionEvent event) throws FileNotFoundException {
        String name = inputName.getText();
        String category = inputCategory.getText();
        Integer quantity=null;
        Integer price=null;
        Integer counter = 0;

        try {

            quantity = Integer.parseInt(inputQuantity.getText());
        } catch (NumberFormatException e) {
            counter++;
            message.setText("Quantity must be positive number");
        }
        try {

            price = Integer.parseInt(inputPrice.getText());
        } catch (NumberFormatException e) {
            counter++;
            message.setText("Price must be positive number");
        }

        VerifyNewProduct verifier = new VerifyNewProduct();

        if (verifier.checkProductNameInDB(name)) {
            counter++;
            message.setText("This Product Name is used");
        }
        if (!verifier.checkPrice(price)) {
            counter++;
            message.setText("Price must be positive value");
        }
        if (!verifier.checkQuantity(quantity)) {
            counter++;
            message.setText("Quantity must be positive value");
        }

        if (photoFile == null) {
            counter++;
            message.setText("Enter a image for product");
        }

        if (counter == 0) {
            verifier.addProductToDB(name, price, category, photoFile, quantity);
            message.setText("You add: "+ name);
        }

    }

}
