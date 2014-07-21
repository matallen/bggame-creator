package org.mnl.bgcreator.handlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DraggedHandler<T extends Node> implements EventHandler<MouseEvent> {
  public OnClickHandler<ImageView> onClickHandler;
  public DraggedHandler(OnClickHandler<ImageView> onClickHandler) {
    super();
    this.onClickHandler=onClickHandler;
  }
  @SuppressWarnings("unchecked")
  @Override public void handle(MouseEvent t) {
    ((T) (t.getSource())).setTranslateX(onClickHandler.orgTranslateX+t.getSceneX()-onClickHandler.orgSceneX);
    ((T) (t.getSource())).setTranslateY(onClickHandler.orgTranslateY+t.getSceneY()-onClickHandler.orgSceneY);
  }
}