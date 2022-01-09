package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardController {


    public Label lbldate;
    public Label lbltime;
    public AnchorPane dashBoardContext;

    public void initialize(){
        //======Set Date and Time================
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        lbldate.setText(f.format(date));

        Timeline time=new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            lbltime.setText(
                    currentTime.getHour()+":"+currentTime.getMinute()+":"+
                            currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }
    public void AdministratorLoginOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AdministratorLogin.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void ReceptionistLoginOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ReceptionistLogin.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
