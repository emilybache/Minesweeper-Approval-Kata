package com.minesweep;

/**
 * Created by ton on 19/01/17.
 */
public class MineSweeper {

    private int width;

    private int height;

    private char[][] clues;

    public static void main(String[] args) {
        MineSweeper ms = new MineSweeper(10,10);
        System.out.println("ms.clues[0,0] = " + ms.clues[0][0]);
        System.out.println("Character.valueOf(ms.clues[0][0]) = " + Character.valueOf(ms.clues[0][0]));
    }

    public MineSweeper(int width, int height) {
        this.width = width;
        this.height = height;
        clues = new char[height][width];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                clues[h][w] = '0';
            }
        }
    }

    public String getClues() {
        String cluesStr = "";
        for (int h=0; h<this.height; h++) {
            for (int w = 0; w < this.width; w++) {

            }
            cluesStr += "\n";
        }
        return cluesStr;
    }


}
