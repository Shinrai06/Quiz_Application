package com.example.partb;

import javafx.scene.control.Alert;

public class alert {
    private static Alert a = new Alert(Alert.AlertType.NONE);
    public static void alertMsg(Alert.AlertType type, String msg){
        a.setAlertType(type);
        if(type == Alert.AlertType.ERROR){
            a.setTitle("Error!!!");
        }else if(type == Alert.AlertType.CONFIRMATION){
            a.setTitle("Completed!!!");
        }else if(type == Alert.AlertType.INFORMATION){
            a.setTitle("Alert!!!");
        }else if(type == Alert.AlertType.WARNING){
            a.setTitle("Warning!!!");
        }
        a.setHeaderText(msg);
        a.show();
    }
}
