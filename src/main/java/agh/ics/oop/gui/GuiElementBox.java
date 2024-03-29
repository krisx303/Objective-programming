package agh.ics.oop.gui;

import agh.ics.oop.world.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox {

    private VBox vBox;
    private String lastResourcePath;
    private String lastLabel;

    private final IMapElement element;
    public GuiElementBox(IMapElement element) {
        this.element = element;
        loadResource(element.getResourcePath());
    }

    private void loadResource(String resourcePath){
        Image image = ResourceLoader.loadImageResource(resourcePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        this.vBox = new VBox();
        Label label = new Label(element.getLabel());
        this.vBox.getChildren().add(imageView);
        this.vBox.getChildren().add(label);
        this.vBox.setAlignment(Pos.CENTER);
        this.lastResourcePath = resourcePath;
        this.lastLabel = element.getLabel();
    }

    public VBox getVBox() {
        return this.vBox;
    }

    public void checkForUpdate() {
        if(!lastResourcePath.equals(element.getResourcePath()) || !lastLabel.equals(element.getLabel())){
            loadResource(element.getResourcePath());
        }
    }
}
