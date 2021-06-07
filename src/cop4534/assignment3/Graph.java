package cop4534.assignment3;

import java.awt.geom.Point2D;
import java.math.BigInteger;
import java.util.*;

/*
Name: Jacob Williams
Section: COP 4534 - U01
Panther ID: 6182345
 */
public class Graph {
    private int verticesNumber;
    private List<Point2D> coordinatePositions = new ArrayList<Point2D>();
    private int matrix[][];
    private int count = 0;
    private int distance = 0;
    private int localSearchDistance = 0;

    public int getLocalSearchDistance() {
        return localSearchDistance;
    }

    public int getDistance() {
        return distance;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setCoordinatePositions(List<Point2D> coordinatePositions) {
        this.coordinatePositions = coordinatePositions;
    }

    // Simple constructor
    public Graph(int verticesNumber, List<Point2D> coordinatePositions) {
        this.verticesNumber = verticesNumber;
        this.coordinatePositions = coordinatePositions;
        matrix = new int[verticesNumber][verticesNumber];

        for (int i = 0; i < verticesNumber; i++) {
            for (int j = 0; j < verticesNumber; j++) {
                int distance = (int) coordinatePositions.get(i).distance(coordinatePositions.get(j));
                matrix[i][j] = distance;
                matrix[j][i] = distance;
            }
        }

    }

    // printing out Points
    public void printCoordinates() {
        for (int i = 0; i < coordinatePositions.size(); i++) {
            System.out.println("(" + (int) coordinatePositions.get(i).getX() + ", "
                    + (int) coordinatePositions.get(i).getY() + ")");
        }
    }

    public void printMatrix() {
        System.out.println();
        for (int i = 0; i < verticesNumber; i++) {
            for (int j = 0; j < verticesNumber; j++) {
                System.out.format("%8s", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public int[] getAdjacentVertices(int v) {
        int[] vert = new int[verticesNumber];
        int total = 0;
        for (int i = 0; i < verticesNumber; i++) {
            if (matrix[v][i] != 0) {
                vert[total] = i;
                total++;
            }
        }
        return Arrays.copyOf(vert, total);
    }

    private int minDistance(boolean[] visited, int[] distance) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < verticesNumber; i++) {
            if (!visited[i]) {
                if (distance[i] <= min) {
                    min = distance[i];
                    index = i;
                }
            }
        }
        return index;
    }

    public void allShortestPaths(int[] paths, int[] distances, int vertex) {
        boolean[] visited = new boolean[verticesNumber];

        for (int i = 0; i < verticesNumber; i++) {
            visited[i] = false;
            paths[i] = -1;
            distances[i] = Integer.MAX_VALUE;
        }

        distances[vertex] = 0;

        for (int i = 0; i < verticesNumber; i++) {
            int w = minDistance(visited, distances);
            visited[w] = true;

            int[] adj = getAdjacentVertices(w);
            for (int u : adj) {
                if (!visited[u]) {
                    if (distances[w] + matrix[w][u] < distances[u]) {
                        distances[u] = distances[w] + matrix[w][u];
                        paths[u] = w;
                    }
                }
            }
        }

    }

    //git change
    public int[] getPath(int s, int t, int[] p) {
        int[] shortestPath = new int[p.length];

        int current = t;
        int total = 0;
        while (current != s) {
            shortestPath[total] = current;
            current = p[current];
            total++;
        }
        shortestPath[total++] = s;
        shortestPath = Arrays.copyOf(shortestPath, total);

        for (int i = 0; i < total / 2; i++) {
            int temp = shortestPath[i];
            shortestPath[i] = shortestPath[total - 1 - i];
            shortestPath[total - 1 - i] = temp;
        }

        return shortestPath;
    }

    public int[] TSP_exhaustiveSearch(int[] shortestRoute) {
        // initialize shortestRoute
        for (int i = 0; i < verticesNumber; i++) {
            shortestRoute[i] = i;
        }

        int[] a = new int[verticesNumber];
        TSP_exhaustiveSearch(shortestRoute, a, 0);

        distance = totalDistance(shortestRoute);
        shortestRoute[verticesNumber] = shortestRoute[0];
        return shortestRoute;
    }

    private void TSP_exhaustiveSearch(int[] shortestRoute, int[] a, int k) {
        if (k == a.length) {
            if (totalDistance(a) < totalDistance(shortestRoute)) {
                System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
            }
            if (count == 0) {
                System.out.println("0%");
            }
            count++;

            long temp = 362880;
            switch (verticesNumber) {
                case 15:
                    temp = 1307674368000L;
                    break;
                case 14:
                    temp = 87178291200L;
                    break;
                case 13:
                    temp = 6227020800L;
                    break;
                case 12:
                    temp = 479001600;
                    break;
                case 11:
                    temp = 39916800;
                    break;
                case 10:
                    temp = 3628800;
                    break;
                default:
                    break;
            }
            double answer = ((double) count / temp) * 100;
            if (answer % 5 == 0) {
                System.out.format("%.0f%%%n", answer);
            }


        } else {
            ArrayList<Integer> Sk = constructCandidateSet(a, k);
            for (int s : Sk) {
                a[k] = s;
                TSP_exhaustiveSearch(shortestRoute, a, k + 1);
            }

        }
    }

    private void printArray(int[] a) {
        for (int v : a) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    private ArrayList<Integer> constructCandidateSet(int[] a, int k) {
        ArrayList<Integer> candidates = new ArrayList<>();
        boolean[] b = new boolean[a.length];

        for (int i = 0; i < k; i++) {
            b[a[i]] = true;
        }

        for (int i = 0; i < a.length; i++) {
            if (!b[i])
                candidates.add(i);
        }

        return candidates;
    }

    private int totalDistance(int[] a) {
        int n = a.length;

        // add weights of all edges in the path
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            int weight = matrix[a[i]][a[(i + 1) % n]];
            totalWeight += weight;
        }
        return totalWeight;
    }

    public void randomPermutation(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        Random rnd = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int randomLocation = rnd.nextInt(i + 1);

            if (randomLocation != i) {
                int temp = a[i];
                a[i] = a[randomLocation];
                a[randomLocation] = temp;
            }
        }

    }

    public int[] TSP_localSearch(int[] shortestRoute) {
        int bestDistance;

        int[] a = new int[verticesNumber];
        randomPermutation(a);

        System.arraycopy(a, 0, shortestRoute, 0, a.length);
        // System.out.println("shortestRoute is " + Arrays.toString(a));
        bestDistance = totalDistance(shortestRoute);

        boolean betterSolutionFound;

        do {
            betterSolutionFound = false;
            PermutationNeighborhood pn = new PermutationNeighborhood(shortestRoute);
            while (pn.hasNext()) {
                a = pn.next();
                int currentDistance = totalDistance(a);
                if (currentDistance < bestDistance) {
                    System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
                    // System.out.println("This is shortestRoute: " +
                    // Arrays.toString(shortestRoute));
                    bestDistance = currentDistance;
                    betterSolutionFound = true;
                }
            }
        } while (betterSolutionFound);

        localSearchDistance = bestDistance;

        return shortestRoute;
    }

    public int TSP_randomSampling(int[] shortestRoute) {
        int numberOfSamples = 10;
        int bestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfSamples; i++) {
            int[] a = new int[verticesNumber + 1];
            randomPermutation(a);
            int currentDistance = totalDistance(a);
            if (currentDistance < bestDistance) {
                bestDistance = currentDistance;
                System.arraycopy(a, 0, shortestRoute, 0, verticesNumber);
            }
        }

        return bestDistance;
    }

}
