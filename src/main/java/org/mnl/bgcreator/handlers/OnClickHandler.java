package org.mnl.bgcreator.handlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class OnClickHandler<T extends Node> implements EventHandler<MouseEvent>{
  public double orgSceneX,orgSceneY,orgTranslateX,orgTranslateY;
  public OnClickHandler() {
    super();
  }
  @SuppressWarnings("unchecked")
  @Override public void handle(MouseEvent t) {
    orgSceneX = t.getSceneX();
    orgSceneY = t.getSceneY();
    orgTranslateX = ((T)(t.getSource())).getTranslateX();
    orgTranslateY = ((T)(t.getSource())).getTranslateY();
  }
}