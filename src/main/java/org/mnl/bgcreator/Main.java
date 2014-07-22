package org.mnl.bgcreator;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.mnl.bgcreator.domain.Deck;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
  /* Application Constants */
  public static final double tableWidth=1280;
  public static final double tableHeight=1024;
  
  // Launches JavaFX View
  public static void main(String[] args) {
    launch(args);
  }
  
  // You define what you want your screen to look like and its behaviour here
  @Override public void start(Stage stage){
    stage.setTitle("Board Game Designer");
    String gameName="game1"; // so basically it will load stuff from src/main/resources/games/<gameName>
    
    try{
      // this defines the root folder for the game info. TODO: make this drop-down on the UI prior to loading the tabletop?
      File gameFolder=new File("src/main/resources/games", gameName);
      
      final Pane tabletop = new AnchorPane();
//      tabletop.setStyle("tabletop");
      
      final Deck deck=Loader.loadDeck(gameFolder);
      deck.shuffle();
      deck.onClick(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent arg0) {
          // turn over the top card
          tabletop.getChildren().add(deck.takeCard().toDisplay());
        }
      });
      tabletop.getChildren().add(deck.toDisplay());
      
//      tabletop.getChildren().addAll(Loader.loadCards(gameFolder));
      tabletop.setBackground(Loader.loadTabletop(gameFolder));
      
      stage.setScene(new Scene(tabletop, tableWidth, tableHeight));
    
    }catch(Exception e){
      e.printStackTrace();
    }
    stage.show();
  }
  
}
