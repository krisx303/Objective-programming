package agh.ics.oop.gui;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;

public class ResourceLoader {

    private static final HashMap<String, Image> imageViewHashMap = new HashMap<>();

    public static Image loadImageResource(String resourcePath){
        Image image;
        if(imageViewHashMap.containsKey(resourcePath)){
            image = imageViewHashMap.get(resourcePath);
        }else{
            InputStream resourceAsStream = GuiElementBox.class.getResourceAsStream(resourcePath);
            image = new Image(resourceAsStream);
            imageViewHashMap.put(resourcePath, image);
        }
        return image;
    }
}
