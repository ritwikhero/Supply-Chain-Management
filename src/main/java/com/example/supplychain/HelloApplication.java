package com.example.supplychain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static final int height = 600, width = 700, headerBar = 50;
    Pane bodyPane = new Pane();

    private GridPane headerBar(){
        TextField searchText = new TextField();
        Button searchButton = new Button("Search");

        GridPane gridPane = new GridPane();

        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,0,1);
        return gridPane;
    }

    private GridPane loginPage(){
        Label emailLable = new Label("Email");
        Label passwordLable = new Label("Password");

        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        GridPane gridPane = new GridPane();
//first is x coordinate and 2nd is y
        gridPane.add(emailLable,0,0);
        gridPane.add(emailTextField,1,0);
        gridPane.add(passwordLable,0,1);
        gridPane.add(passwordField,1,1);

        return gridPane;

    }
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width,height+headerBar);

        root.getChildren().addAll(loginPage());
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}