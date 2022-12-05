package agh.ics.oop;

public class OptionParser {
    public static MoveDirection[] parse(String[] instructions) throws IllegalArgumentException {
        MoveDirection[] directions = new MoveDirection[instructions.length];
        for (int i = 0; i < instructions.length; i++){
            String instruction = instructions[i];
            MoveDirection direction = MoveDirection.getDirectionByLabel(instruction);
            if(direction == null)
                throw new IllegalArgumentException(instruction + " is not legal move specification");
            directions[i] = direction;
        }
        return directions;
    }
}
