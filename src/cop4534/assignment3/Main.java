package cop4534.assignment3;

/*
Name: Jacob Williams
Section: COP 4534 - U01
Panther ID: 6182345
 */

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Point2D> coordinatePositions = new ArrayList<Point2D>();
        int numberOfVertices;
        int matrix[][];

        //reading text file
        ReadInputFromTextFile r = new ReadInputFromTextFile();

        //setting values from inputs
        coordinatePositions = r.getCoordinatePositions();
        numberOfVertices = r.getNumberOfVertices();

        int[] paths = new int[numberOfVertices];
        int[] distances = new int[numberOfVertices];

        //creating graph and setting matrix
        Graph graph = new Graph(numberOfVertices, coordinatePositions);
        matrix = graph.getMatrix();

        //Printing matrix
        //graph.printMatrix();

        int[] routesLocal = new int[numberOfVertices];
        int[] localPath = graph.TSP_localSearch(routesLocal);
        int[] trueLocalPath = new int[numberOfVertices + 1];
        for (int i = 0; i < localPath.length; i++) {
            trueLocalPath[i] = localPath[i];
        }
        trueLocalPath[trueLocalPath.length - 1] = trueLocalPath[0];
        int localSearchDistance = graph.getLocalSearchDistance();
        localSearchDistance += matrix[localPath.length - 1][0];

        int[] routesExhaustive = new int[numberOfVertices + 1];
        int[] exhaustivePath = graph.TSP_exhaustiveSearch(routesExhaustive);
        int exhaustiveDistance = graph.getDistance();

        //System.out.println(Arrays.toString(localPath));
        //Paint the graph with edges and the final path
        FrameDisplay frame = new FrameDisplay(numberOfVertices, matrix, exhaustivePath, trueLocalPath, coordinatePositions, exhaustiveDistance, localSearchDistance);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

}
