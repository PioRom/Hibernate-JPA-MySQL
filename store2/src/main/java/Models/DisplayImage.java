package Models;

import Entities.Product;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class DisplayImage {

    public Image display(Product product) throws IOException, SQLException {


        String imagePath = "Image.png";
        System.out.println(product.getName());
        Blob photo = product.getPhoto();

        byte[] photoBlobBytes = photo.getBytes(1, (int) photo.length());
        FileOutputStream outputStream = new FileOutputStream(imagePath);
        outputStream.write(photoBlobBytes);
        outputStream.close();
        Image image = new Image(new File(imagePath).toURI().toString());
        return image;

    }

    public boolean checkImage(Product product){
        if(product.getPhoto()!=null) return true;
        else return false;
    }
}
