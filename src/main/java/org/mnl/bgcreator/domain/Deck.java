package org.mnl.bgcreator.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

// all start obscured
// click to reveal card
// add card to tabletop pane children

public class Deck {
  private List<Card> cards=new LinkedList<Card>();
  private ImageView view;
  
  public Deck(File reverseOfCard) throws FileNotFoundException{
    view=new ImageView(new Image(new FileInputStream(reverseOfCard)));
  }
  
  public ImageView toDisplay(){
    return view;
  }
  
  public void addCard(Card card){
    cards.add(card);
  }
  
  private static Random random=new Random();
  public int random(int lower, int upper){
    return random.nextInt(upper - (lower - 1)) + lower;
  }
  
  public void shuffle(){
    List<Card> newCards=new LinkedList<Card>();
    while(cards.size()>0){
      int cardNumeric=random(1, cards.size())-1;
      newCards.add(cards.remove(cardNumeric));
    }
    cards=newCards;
  }

  public void onClick(EventHandler<MouseEvent> handler) {
    view.setOnMouseClicked(handler);
  }

  public Card takeCard() {
    return cards.remove(0);
  }
  
  
}
