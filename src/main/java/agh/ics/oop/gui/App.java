package agh.ics.oop.gui;

import agh.ics.oop.*;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.world.AbstractWorldMap;
import agh.ics.oop.world.GrassField;
import agh.ics.oop.world.IMapElement;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;

public class App extends Application implements IFrameChangeObserver {

    private AbstractWorldMap map;
    private final Font font = new Font(30);
    private final int cellSize = 40;

    private GridPane gridPane;

    private Thread engineThread;
    private Stage stage;

    private HashMap<IMapElement, GuiElementBox> guiElements;

    @Override
    public void init() throws Exception {
        super.init();
        try {
            guiElements = new HashMap<>();
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = OptionParser.parse(args);
//            IWorldMap map = new RectangularMap(10, 5);
            map = new GrassField(6);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            SimulationEngine engine = new SimulationEngine(directions, map, positions);
            engine.setObserver(this);
            engine.setMoveDelay(300);
            engineThread = new Thread(engine);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        primaryStage.setTitle("Farm");
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        int x = map.getUpperRight().x - map.getLowerLeft().x;
        int y = map.getUpperRight().y - map.getLowerLeft().y;
        Scene scene = new Scene(gridPane, x*40+90, y*40+80);
        update();
        primaryStage.setScene(scene);
        primaryStage.show();
        engineThread.start();
    }

    private void renderGrid(GridPane gridPane) {
        int xStart = map.getLowerLeft().x;
        int yStart = map.getLowerLeft().y;
        int height = map.getUpperRight().y - map.getLowerLeft().y + 1;
        int width = map.getUpperRight().x - map.getLowerLeft().x + 1;

        addColumns(gridPane, xStart, width + 1);
        addRows(gridPane, yStart, height);

        renderLabel(gridPane, 0, 0, "y/x");


        map.getMapElements().forEach(element -> {
            int x = element.getPosition().x-xStart + 1;
            int y = height-(element.getPosition().y-yStart);
            VBox vBox;
            GuiElementBox eBox;
            if(guiElements.containsKey(element)){
                eBox = guiElements.get(element);
                eBox.checkForUpdate();

            }else{
                eBox = new GuiElementBox(element);
                guiElements.put(element, eBox);
            }
            vBox = eBox.getVBox();
            gridPane.add(vBox, x, y, 1, 1);
        });
    }

    private void addColumns(GridPane gridPane, int startCol, int numOfCol){
        gridPane.getColumnConstraints().add(new ColumnConstraints(cellSize));
        for (int i = 0; i < numOfCol-1; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(cellSize));
            renderLabel(gridPane, i+1, 0, Integer.toString(startCol + i));
        }
    }

    private void addRows(GridPane gridPane, int startRow, int numOfRows){
        for (int i = 0; i < numOfRows; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(cellSize));
            renderLabel(gridPane, 0, numOfRows - i, Integer.toString(startRow + i));
        }
    }

    private void renderLabel(GridPane gridPane, int x, int y, String value){
        Label label = new Label(value);
        gridPane.add(label, x, y, 1, 1);
        GridPane.setHalignment(label, HPos.CENTER);
        label.setFont(font);
    }

    @Override
    public void updateFrame() {
        Platform.runLater(this::update);
    }

    private void update(){
        Node node = gridPane.getChildren().get(0);
        gridPane.getChildren().clear();
        gridPane.getChildren().add(0,node);
        gridPane.setGridLinesVisible(true);
        int x = map.getUpperRight().x - map.getLowerLeft().x;
        int y = map.getUpperRight().y - map.getLowerLeft().y;
        stage.setHeight(y*40+120);
        stage.setWidth(x*40+90);
        renderGrid(gridPane);
    }
}
