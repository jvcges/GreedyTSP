import java.util.ArrayList;
import java.text.*;

public class GreedyTSP {
    
    static ArrayList<Edge> edges = new ArrayList<Edge>();
    static ArrayList<Edge> tourEdges = new ArrayList<Edge>();
    static double greedyDistance = 0.0;

		public static Integer totalDistance(Integer a, Integer b) {
			return a+b;
		}
    
    public void Greedy (int node, long seed) {
	DecimalFormat df = new DecimalFormat("0.00");
	
	Graph g = new Graph(node, seed);

	long startTime = System.currentTimeMillis();
	
	double[][] adjMatrix = g.getAdjacencyMatrix();
	edges = generateEdges(adjMatrix);
	edges = quicksort(0, edges.size() - 1, edges);

	ArrayList<Integer> visited = new ArrayList<Integer>();
	visited = greedyTSP(edges, visited, 0);
	visited.add(0);

	double[][] greedyMatrix = generateGreedyMatrix(visited, adjMatrix);
	
	for (int i = 0; i < edges.size(); i++) {
	    for (int j = 0; j < visited.size() - 1; j++) {
		if (((visited.get(j) == edges.get(i).getLeft()) &&
		     (visited.get(j+1) == edges.get(i).getRight()))) {
		    if (edges.get(i).getRight() != edges.get(i).getLeft()) {
			tourEdges.add(edges.get(i));
			greedyDistance += edges.get(i).getDist();
		    }
		}
	    }
	}
	
	long endTime = System.currentTimeMillis();
	    

	if (node <= 10) {
	    System.out.println(g.getVerticesString(g.getVertices()));
	

	    System.out.println("Matriz de distâncias entre nodos:\n\n" + g.getMatrixString() + "\n");
	

	    System.out.println("Matriz de distâncias entre nodos após algoritmo guloso:\n");
	    
	    for (int i = 0; i < greedyMatrix.length; i++) {
		System.out.print("      " + i);
	    } 
	    
	    for (int i = 0; i < greedyMatrix.length; i++) {
		System.out.print("\n\n" + i + "   ");
		for (int j = 0; j < greedyMatrix[i].length; j++) {
		    System.out.print(df.format(greedyMatrix[i][j]) + "   ");
		}
	    }
	    System.out.println("\n");
	    
	    for (int i = 0; i < tourEdges.size(); i++) {
		System.out.println(tourEdges.get(i).toString());
	    }
	}
	
	System.out.print("\nDistância total: " + df.format(greedyDistance) + "\nCaminho: " );
	for (int i = 0; i < visited.size(); i++) {
	    System.out.print(" " + visited.get(i));
	}
	
	System.out.println("\nTempo de execução: " + (endTime - startTime) + " milissegundos");
    }
    
    public static ArrayList<Edge> generateEdges(double[][] matrix) {
	ArrayList<Edge> edges = new ArrayList<Edge>();
	
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[0].length; j++) {
		edges.add(new Edge(i, j, matrix[i][j]));
	    }
	}
	
	return edges;
    }
    
    public static double[][] generateGreedyMatrix(ArrayList<Integer> visited, double[][] oldMatrix) {
	int n = visited.size() - 1;
	double[][] newMatrix = new double[n][n];
	int r, c = 0;
	
	for (int i = 0; i < visited.size() - 1; i++) {
	    r = visited.get(i);
	    c = visited.get(i+1);
	    newMatrix[r][c] = oldMatrix[r][c];
	    newMatrix[c][r] = oldMatrix[c][r];
	}
	
	return newMatrix;
    }
    
    public static ArrayList<Edge> quicksort(int low, int high, ArrayList<Edge> edges) {
	if (edges.size() <= 1) {
	    return edges;
	}
		
	int pivotIndex = low + (high-low) / 2;
	Edge pivot = edges.get(pivotIndex);
    	
	int i = low, j = high;
	while (i <= j) {
	     
	    while ((edges.get(i).getDist() < pivot.getDist()) ||
		   ((edges.get(i).getDist() == pivot.getDist()) &&
		    (edges.get(i).getLeft() < pivot.getLeft())) ||
		   ((edges.get(i).getDist() == pivot.getDist()) &&
		    (edges.get(i).getLeft() == pivot.getLeft()) &&
		    (edges.get(i).getRight() < pivot.getRight()))) {
    		i++;
	    }
	    while ((edges.get(j).getDist() > pivot.getDist()) ||
		   ((edges.get(j).getDist() == pivot.getDist()) &&
		    (edges.get(j).getLeft() > pivot.getLeft())) ||
		   ((edges.get(j).getDist() == pivot.getDist()) &&
		    (edges.get(j).getLeft() == pivot.getLeft()) &&
		    (edges.get(j).getRight() > pivot.getRight()))) {
    		j--;
	    }
       
	    if (i <= j) {
		Edge temp = edges.get(i);
		edges.set(i, edges.get(j));
		edges.set(j, temp);
		i++;
		j--;
	    }
	}
	
	if (low < j) {
	    edges = quicksort(low, j, edges);
	}
	if (i < high) {
	    edges = quicksort(i, high, edges);
	}
	
	return edges;
    }
    
    public static ArrayList<Integer> greedyTSP(ArrayList<Edge> edges, ArrayList<Integer> visited, int current) {
    
	visited.add(current);
	
	for (int i = 0; i < edges.size(); i++) {
	    if ((edges.get(i).getLeft() == current) &&
		(visited.contains(edges.get(i).getRight()) == false)) {
		visited = greedyTSP(edges, visited, edges.get(i).getRight());
		break;
	    }
	    else if ((edges.get(i).getRight() == current) &&
		(visited.contains(edges.get(i).getLeft()) == false)) {
		visited = greedyTSP(edges, visited, edges.get(i).getLeft());
		break;
	    }
	}
	
	return visited;
    }
}