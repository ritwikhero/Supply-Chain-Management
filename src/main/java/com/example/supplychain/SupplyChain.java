package com.example.supplychain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChain extends Application {
    public static final int height = 600, width = 700, headerBar = 50;
    Pane bodyPane = new Pane();

    Login login = new Login();

    ProductDetails productDetails = new ProductDetails();

    Button globalLoginButton ;
    Label customerEmailLable = null;

    String customerEmail = null;

    private GridPane headerBar(){
        TextField searchText = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName = searchText.getText();
                productDetails.getProductsByName(productName);
                //clear body and put this new pane in the body
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getProductsByName(productName));
            }
        });
        globalLoginButton = new Button("Log in");
        globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                globalLoginButton.setDisable(true);

            }
        });

        customerEmailLable = new Label("Welcome user");

        GridPane gridPane = new GridPane();
        //Rearranging the structure
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        //Align to  the centre
        gridPane.setAlignment(Pos.CENTER);
        //vertical and horizontal gap
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        //adding colour
        //gridPane.setStyle("-fx-background-color: #BFEAF5");
        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);

        gridPane.add(globalLoginButton,2,0);
        gridPane.add(customerEmailLable,3,0);
        return gridPane;
    }
    private GridPane footerBar(){
        //TextField searchText = new TextField();
        Button addToCartButton = new Button("Add To Cart");
        Button buyNowButton = new Button("Buy Now");



        GridPane gridPane = new GridPane();
        //Rearranging the structure
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        //Align to  the centre
        gridPane.setAlignment(Pos.CENTER);
        //vertical and horizontal gap
        gridPane.setTranslateY(headerBar+height+5);
        gridPane.setVgap(5);
        gridPane.setHgap(20);
        //adding colour
        //gridPane.setStyle("-fx-background-color: #BFEAF5");
        gridPane.add(addToCartButton,0,0);
        gridPane.add(buyNowButton,1,0);


        return gridPane;
    }

    private GridPane loginPage(){
        Label emailLable = new Label("Email");
        Label passwordLable = new Label("Password");
        Label messageLable = new Label("I am Message");

        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email = emailTextField.getText();
                String password = passwordField.getText();

//                messageLable.setText(email+" $$ "+password);
                if(login.customerLogin(email,password)){
                    messageLable.setText("Login Successful");
                    customerEmail = email;
                    globalLoginButton.setDisable(true);
                    customerEmailLable.setText("Welcome : "+ customerEmail);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                }else{
                    messageLable.setText("Login Failed");
                }
            }
        });

        GridPane gridPane = new GridPane();
        //Rearranging the structure
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        //Align to  the centre
        gridPane.setAlignment(Pos.CENTER);
        //vertical and horizontal gap
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        //adding colour
        gridPane.setStyle("-fx-background-color: #BFEAF5");
//first is x coordinate and 2nd is y
        gridPane.add(emailLable,0,0);
        gridPane.add(emailTextField,1,0);
        gridPane.add(passwordLable,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(loginButton,0,2);
        gridPane.add(messageLable,1,2);
        return gridPane;

    }
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width,height+2*headerBar);

        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerBar);
        bodyPane.getChildren().addAll(productDetails.getAllProducts()/*loginPage()*/);
        root.getChildren().addAll(headerBar(),  bodyPane, footerBar());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}