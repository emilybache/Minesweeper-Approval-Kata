package com.minesweep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by ton on 19/01/17.
 */
public class MinefieldScanner {

    private int width;

    private int height;

    private Map<Coords, Marker> clues;

    public MinefieldScanner(int height, int width) {
        this.width = width;
        this.height = height;
        this.clues = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {
        scanMineFields(System.in);
    }

    private static void scanMineFields(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        int count = 1;
        while (true) {
            String line = reader.readLine();
            if ("0 0".equals(line)) {
                break;
            }
            Scanner scanner = new Scanner(line);
            int height = scanner.nextInt();
            int width = scanner.nextInt();
            scanner.close();
            MinefieldScanner minefieldScanner = new MinefieldScanner(height, width);
            for (int h = 0; h < height; h++) {
                line = reader.readLine();
                for (int w = 0; w < width; w++) {
                    if (line.charAt(w) == '*') {
                        minefieldScanner.placeMine(h, w);
                    }
                }
            }
            System.out.println("Field " + count + ":");
            System.out.println(minefieldScanner.getClues());
            count++;
        }
    }

    private String getClues() {
        StringBuilder cluesStr = new StringBuilder();
        for (int h=0; h<this.height; h++) {
            for (int w = 0; w < this.width; w++) {
                Optional<Marker> marker = Optional.ofNullable(clues.get(coords(h, w)));
                cluesStr.append(marker.orElse(zero()).toString());
            }
            cluesStr.append("\n");
        }
        return cluesStr.toString();
    }

    private void placeMine(int h, int w) {
        this.clues.put(coords(h, w), bomb());
        for (int dh = -1; dh < 2; dh++) {
            for (int dw = -1; dw < 2; dw++) {
                Coords coordinates = coords(h + dh, w + dw);
                if (clues.containsKey(coordinates)) {
                    clues.get(coordinates).incrementCount();
                } else {
                    clues.put(coordinates, one());
                }
            }
        }
    }

    private Coords coords(int h, int w) {
        return new Coords(h, w);
    }

    private Marker bomb() {
        return new Marker(true);
    }

    private Marker one() {
        return new Marker(1);
    }

    private Marker zero() {
        return new Marker(0);
    }

    /**
     * Class to represent the coordinates in a minefield.
     */
    private class Coords {
        final int h;
        final int w;

        private Coords(int h, int w) {
            this.h = h;
            this.w = w;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Coords coords = (Coords) o;

            if (h != coords.h) {
                return false;
            }
            return w == coords.w;

        }

        @Override
        public int hashCode() {
            int result = h;
            result = 31 * result + w;
            return result;
        }
    }

    /**
     * Class to represent the markers in a minefield.
     * A marker is either a bomb ('*') or a count.
     */
    private class Marker {

        private boolean isBomb;

        private int counter = 0;

        Marker(boolean isBomb) {
            this.isBomb = isBomb;
        }

        Marker(int counter) {
            this.counter = counter;
        }

        void incrementCount() {
            if (!isBomb) {
                counter++;
            }
        }

        @Override
        public String toString() {
            if (isBomb) {
                return "*";
            }
            return Integer.toString(counter);
        }
    }

}
