package cop4534.assignment3;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Name: Jacob Williams
Section: COP 4534 - U01
Panther ID: 6182345
 */
public class ReadInputFromTextFile {
    int numberOfVertices;
    List<Point2D> coordinatePositions = new ArrayList<Point2D>();

    public ReadInputFromTextFile() {
        readGraph();
    }

    /**
     * Reads graph from text file (entries as given in COP4534 3rd assignment)
     * and prints it
     */
    public void readGraph() {
        File input = new File("src/cop4534/assignment3/givenXnY.txt");

        Scanner in = null;
        try {
            in = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        numberOfVertices = 0;

        while (true) {
            assert in != null;
            if (!in.hasNextLine()) break;
            numberOfVertices = in.nextInt();
            //System.out.println(numberOfVertices);

            for (int i = 0; i < numberOfVertices; i++) {
                int x;
                x = in.nextInt();
                int y;
                y = in.nextInt();
                Point2D p = new Point2D.Double(x, y);
                coordinatePositions.add(p);
            }
        }
        in.close();

    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public List<Point2D> getCoordinatePositions() {
        return coordinatePositions;
    }
}