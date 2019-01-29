
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.io.*;
import java.lang.String;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Pandafi");
        primaryStage.setScene(new Scene(root, 587, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException, IOException{

        launch(args);
        Test();

// Statement statement = conn.createStatement();
//results.close();
//statement.close();
//conn.close();
    }
    public static void Test()throws SQLException, IOException{
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        String serverName = "csor12c.dhcp.bsu.edu";
        String portNumber = "1521";
        String sid = "or12cdb";
        String url = "jdbc:oracle:thin:@" + serverName+ ":"+ portNumber+ ":"+sid;

        Connection conn = DriverManager.getConnection(url, "mjlewis4", "2519");


        System.out.println("Connected to SQL Server.");

    }
}
                          