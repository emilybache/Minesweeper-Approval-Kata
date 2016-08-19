#!/usr/bin/env python

from collections import defaultdict
import logging

logger = logging.getLogger("Minefield")

class Minefield:
    def __init__(self, height, width):
        self.height = height
        self.width = width
        self.clues = defaultdict(int)
        logger.info("hello world!")
    
    def get_clues(self):
        clues_str = ""
        for i in range(self.height):
            for j in range(self.width):
                clues_str += str(self.clues[ (i, j) ] or ".")
                clues_str += " "
            clues_str += "\n"
        return clues_str
    
    def place_mine(self, x, y):
        self.clues[ (x, y) ] = "*"
        for i in (x-1, x, x+1):
            for j in (y-1, y, y+1):
                if not self.clues[ (i, j) ] == "*":
                    self.clues[ (i, j) ] += 1
        

def MinefieldReader(inputfile):
    while 1:
        shape = inputfile.next().strip()
        if shape == "0 0":
            raise StopIteration()
        height, width = map(int, shape.split())
        field = Minefield(height, width)
        for i in range(height):
            line = inputfile.next()
            double_spaced = len(line)  > width
            for j, cell in enumerate(line):
                if double_spaced: 
                    j = j/2
                if cell == "*":
                    logger.info("found mine at (%s, %s)", i, j)
                    field.place_mine(i, j)
            
        yield field

def main(inputfile, outputfile):
    for i, minefield in enumerate(MinefieldReader(inputfile)):
        outputfile.write("Field #%s:\n" % (i+1))
        outputfile.write(minefield.get_clues())
        outputfile.write("\n")

if __name__ == '__main__':
    import sys
    if "--help" in sys.argv:
        print """\
Input minefields to standard input, this script will write a the corresponding 'clues' to standard output.

Several minefields may be input in this format:

4 4
*...
....
.*..
....
3 5
**...
.....
.*...
0 0
"""
    else:
        logging.basicConfig(filename="minesweeper.log", level=logging.INFO, format='%(asctime)s %(message)s')
        main(sys.stdin, sys.stdout)
