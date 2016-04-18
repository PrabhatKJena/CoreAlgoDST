package edu.pk.algo;

import java.util.HashMap;
import java.util.Map;

public class MarsRover {
    public static void main(String[] args) {
        int xMax = 5;
        int yMax = 5;
        Rover rover = new Rover(1, 2, Direction.N);
        processInstruction(rover, "LMLMLMLMM");
    }

    private static void processInstruction(Rover rover, String str) {
        char ch = '\0';
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch == 'M') {
                moveForward(rover);
            } else {
                Direction turn = DirectionManager.turn(rover.dir, ch);
                rover.dir = turn;
            }
        }
    }

    private static void moveForward(Rover rover) {

    }

    static class Rover {
        int xPos;
        int yPos;
        Direction dir;

        Rover(int x, int y, Direction d) {
            xPos = x;
            yPos = y;
            dir = d;
        }

        @Override
        public String toString() {
            return xPos + " " + yPos + " " + dir.name();
        }
    }

    static enum Direction {
        N, S, E, W;
    }

    static enum Turn {
        L, R;
    }

    static class DirectionManager {
        static Map<String, Direction> map = new HashMap<>();

        static {
            map.put("NL", Direction.W);
            map.put("NR", Direction.E);
            map.put("SL", Direction.E);
            map.put("SR", Direction.W);
            map.put("WL", Direction.S);
            map.put("WR", Direction.N);
            map.put("EL", Direction.N);
            map.put("ER", Direction.S);
        }

        public static Direction turn(Direction dir, char turn) {
            Turn turn1 = Turn.valueOf(String.valueOf(turn));
            return map.get(dir.name() + "" + turn1.name());
        }
    }

}




