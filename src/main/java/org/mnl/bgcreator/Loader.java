package org.mnl.bgcreator;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;

import org.apache.log4j.Logger;
import org.mnl.bgcreator.domain.Card;
import org.mnl.bgcreator.domain.Deck;
import org.mnl.bgcreator.handlers.DraggedHandler;
import org.mnl.bgcreator.handlers.OnClickHandler;

import com.google.common.collect.Lists;

public class Loader {
  public static final Logger log=Logger.getLogger(Loader.class);
  
  public static Background loadTabletop(File gameFolder) throws FileNotFoundException{
    Image image=new Image(new FileInputStream(new File(gameFolder, "tabletop.jpg")));
    return new Background(
          new BackgroundImage(
              image, 
              NO_REPEAT,
              NO_REPEAT,
              BackgroundPosition.DEFAULT, 
              new BackgroundSize(image.getWidth(), image.getHeight(), false, false, false, false))
          );
  }

  public static List<ImageView> loadCards(File gameFolder) throws FileNotFoundException{
    List<ImageView> result=Lists.newArrayList();
    double cardPositionOffsetX=0;
    
    double gapBetweenCards=5;
    
    for(File card:new File(gameFolder, "cards").listFiles()){
      log.debug("Loading Card ["+card.getName()+"]");
      Image image=new Image(new FileInputStream(card));
      ImageView img=new ImageView(image);
      img.setLayoutX(cardPositionOffsetX);
      cardPositionOffsetX+=image.getWidth()+gapBetweenCards;
      img.setCursor(Cursor.HAND);

      OnClickHandler<ImageView> onClickHandler=new OnClickHandler<ImageView>();
      img.setOnMousePressed(onClickHandler);
      img.setOnMouseDragged(new DraggedHandler<ImageView>(onClickHandler));
      
      result.add(img);
    }
    return result;
  }
  
  public static Deck loadDeck(File gameFolder) throws FileNotFoundException{
    Deck deck=new Deck(new File(gameFolder,"cards-reverse.jpg"));
    
    for(File card:new File(gameFolder, "cards").listFiles()){
      log.debug("Loading Card ["+card.getName()+"]");
      
      Image image=new Image(new FileInputStream(card));
      ImageView img=new ImageView(image);
      img.setCursor(Cursor.HAND);
      
      OnClickHandler<ImageView> onClickHandler=new OnClickHandler<ImageView>();
      img.setOnMousePressed(onClickHandler);
      img.setOnMouseDragged(new DraggedHandler<ImageView>(onClickHandler));
      
      deck.addCard(new Card(img));
    }
    return deck;
  }
}
