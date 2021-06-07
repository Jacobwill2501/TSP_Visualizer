package cop4534.assignment3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
Name: Jacob Williams
Section: COP 4534 - U01
Panther ID: 6182345
 */
public class GraphDisplay extends JPanel {
    private int numberOfVertices;
    private int[][] matrix;
    private int[] exhaustivePath;
    private List<Point2D> coordinatePositions = new ArrayList<Point2D>();
    private int exhaustiveDistance;
    private int[] localPath;
    private int localSearchDistance;

    public void setExhaustiveDistance(int exhaustiveDistance) {
        this.exhaustiveDistance = exhaustiveDistance;
    }

    public void setLocalSearchDistance(int localSearchDistance) {
        this.localSearchDistance = localSearchDistance;
    }

    public void setCoordinatePositions(List<Point2D> coordinatePositions) {
        this.coordinatePositions = coordinatePositions;
    }

    public void setLocalPath(int[] localPath) {
        this.localPath = localPath;
    }

    public void setNumberOfVertices(int val) {
        this.numberOfVertices = val;
    }

    public void setExhaustivePath(int[] exhaustivePath) {
        this.exhaustivePath = exhaustivePath;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void paintComponent(Graphics g) {

        int[][] locations = new int[numberOfVertices][2];
        int leftX;
        int topY;

        int j = 0;

        //storing locations, changed to not draw node for look
        for (int i = 0; i < numberOfVertices; i++) {

            leftX = (int) coordinatePositions.get(i).getX();
            topY = (int) coordinatePositions.get(i).getY();

            locations[i][0] = leftX + 12;
            locations[i][1] = topY + 12;
        }

        //Drawing edges and their weights
        for (int i = 0; i < numberOfVertices; i++) {
            for (int k = i; k < numberOfVertices; k++) {
                if (matrix[i][k] != 0) {
                    drawEdge(g, locations, i, k);
                }
            }
        }

        for (int i = 0; i < exhaustivePath.length - 1; i++) {
            drawFinalEdges(g, locations, exhaustivePath[i], exhaustivePath[i + 1]);
        }

        for (int i = 0; i < localPath.length - 1; i++) {
            drawLocalPathEdges(g, locations, localPath[i], localPath[i + 1]);
        }

        //drawing the actual nodes over edges for aesthetic purposes
        for (int i = 0; i < numberOfVertices; i++) {
            leftX = (int) coordinatePositions.get(i).getX();
            topY = (int) coordinatePositions.get(i).getY();

            drawNode(g, j, leftX, topY);
            j++;
        }
        g.setColor(Color.RED);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        g.drawString("The order of local search path is: " + Arrays.toString(localPath), getWidth() / 6, getHeight() - 200);
        g.drawString("The total distance of local search path is : " + localSearchDistance, getWidth() / 6, getHeight() - 150);
        g.setColor(Color.BLUE);
        g.drawString("The order of exhaustive search path is: " + Arrays.toString(exhaustivePath), getWidth() / 6, getHeight() - 100);
        g.drawString("The total distance of exhaustive search path is : " + exhaustiveDistance, getWidth() / 6, getHeight() - 50);
    }

    public void drawNode(Graphics g, int currentVertex, int leftX, int topY) {
        String asString = String.valueOf(currentVertex);

        g.setColor(Color.ORANGE);
        g.fillOval(leftX, topY, 30, 30);
        g.setColor(Color.BLACK);
        g.drawOval(leftX, topY, 30, 30);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        g.drawString(asString, leftX + 12, topY + 20);
    }

    public void drawEdge(Graphics g, int[][] locations, int vertex1, int vertex2) {
        Graphics2D g2 = (Graphics2D) g;
        int x1 = locations[vertex1][0];
        int y1 = locations[vertex1][1];
        int x2 = locations[vertex2][0];
        int y2 = locations[vertex2][1];

        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, x2, y2);

    }

    public void drawFinalEdges(Graphics g, int[][] locations, int vertex1, int vertex2) {

        int x1 = locations[vertex1][0];
        int y1 = locations[vertex1][1];
        int x2 = locations[vertex2][0];
        int y2 = locations[vertex2][1];
        int weight = matrix[vertex1][vertex2];
        String weightAsString = String.valueOf(weight);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g.setColor(Color.BLUE);
        g2.draw(new Line2D.Float(x1, y1, x2, y2));
        g2.setStroke(new BasicStroke(2));

        int xForString = x1 + (x2 - x1) / 3;
        int yForString = y1 + (y2 - y1) / 3;

        g.setColor(Color.BLUE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString(weightAsString, xForString - 10, yForString - 10);
    }

    public void drawLocalPathEdges(Graphics g, int[][] locations, int vertex1, int vertex2) {

        int x1 = locations[vertex1][0];
        int y1 = locations[vertex1][1];
        int x2 = locations[vertex2][0];
        int y2 = locations[vertex2][1];
        int weight = matrix[vertex1][vertex2];
        String weightAsString = String.valueOf(weight);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.setColor(Color.RED);
        g2.draw(new Line2D.Float(x1, y1, x2, y2));
        g2.setStroke(new BasicStroke(2));

        int xForString = x1 + (x2 - x1) / 3;
        int yForString = y1 + (y2 - y1) / 3;

        g.setColor(Color.RED);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString(weightAsString, xForString - 10, yForString - 10);
    }


}
