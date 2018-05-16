package App;

import Util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

public class MyApp extends Application {

    public static SessionFactory sessionFactory = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("StartPanel.fxml"));
        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root, 306, 223));
        primaryStage.show();
        System.out.println("Application has started");
    }

    public static void main(String[] args) {
        try {
            sessionFactory = HibernateUtil.buildSessionFactory();
            launch(args);
            System.out.println("Application has closed");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {

            sessionFactory.close();
        }

    }

}