package cop4534.assignment3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/*
Name: Jacob Williams
Section: COP 4534 - U01
Panther ID: 6182345
 */
public class FrameDisplay extends JFrame {

    final int WIDTH = 900;
    final int HEIGHT = 1000;

    public FrameDisplay(int numberOfVertices, int[][] matrix, int[] exhaustivePath, int[] localPath, List<Point2D> coordinatePositions, int exhaustiveDistance, int localSearchDistance) {

        setTitle("Graph Display");
        setSize(WIDTH, HEIGHT);

        GraphDisplay panel = new GraphDisplay();
        panel.setNumberOfVertices(numberOfVertices);
        panel.setMatrix(matrix);
        panel.setExhaustivePath(exhaustivePath);
        panel.setCoordinatePositions(coordinatePositions);
        panel.setExhaustiveDistance(exhaustiveDistance);
        panel.setLocalPath(localPath);
        panel.setLocalSearchDistance(localSearchDistance);
        add(panel);
    }

}
