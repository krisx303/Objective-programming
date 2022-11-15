package agh.ics.oop.out;

import agh.ics.oop.world.IWorldMap;

/** Use this interface to implement different type of display program output.
 * Output is based on class which implements IWorldMap interface.
 * */
public interface IOutput {
    /** Use that function to update output base at actual IWorldMap state. For example, after every Animal move use
     *  this to display new positions on output.*/
    void update();

    /** Init function. Use to prepare output for data. For example create here window where data will be display.*/
    void init(IWorldMap map);
}
