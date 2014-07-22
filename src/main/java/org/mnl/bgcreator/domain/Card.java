package org.mnl.bgcreator.domain;

import java.io.FileNotFoundException;

import javafx.scene.image.ImageView;

public class Card {
  private ImageView view;
  
  public Card(ImageView view){
    this.view=view;
  }

  public ImageView toDisplay(){
    return view;
  }
}
