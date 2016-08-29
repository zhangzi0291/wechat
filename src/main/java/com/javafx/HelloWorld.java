package com.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelloWorld extends Application{
	 public static void main(String[] args) {  
	       launch(args);  
	    }  
	    
	   @Override  
	   public void start(Stage primaryStage) {  
		   WebView webView = new WebView();  
		   WebEngine webEngine = webView.getEngine(); 
		   webEngine.load("http://106.186.18.49/wechat/web/futureweather?area=%E5%8D%97%E4%BA%AC");
	       StackPane root = new StackPane();  
	       
	        System.out.println("Java Version:   " + System.getProperty("java.runtime.version"));
	        System.out.println("JavaFX Version: " + System.getProperty("javafx.runtime.version")  );
	        System.out.println("OS:             " + System.getProperty("os.name") + ", " + System.getProperty("os.arch"));
	        System.out.println("User Agent:     " + webView.getEngine().getUserAgent() );
	       
	       root.getChildren().add(webView);  
	       primaryStage.setScene(new Scene(root, 860, 480));  
	       
	       primaryStage.show();  
	    }  
}
