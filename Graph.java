import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.text.*;

public class Graph {
    int n; 
    private ArrayList<Vertex> vertices;
    private double[][] adjacencyMatrix;
    private DecimalFormat df = new DecimalFormat("0.00");
        
    public Graph(int _n, long seed) {
        
        n = _n;
        
        vertices = new ArrayList<Vertex>();
        adjacencyMatrix = new double[n][n];
        
        Random xGenerator = new Random(seed);
        Random yGenerator = new Random(2 * seed);        
        
        for (int i = 0; i < n; i++) {
            Vertex v = generateUniqueCoordinate(xGenerator, yGenerator);
	    v.setNumber(i);
            vertices.add(v);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = getDist(vertices.get(i), vertices.get(j));
            }
        }
	
    }
            
    public Boolean isInDescendingOrder(ArrayList<Integer> path) {
        boolean isDescending = true;
        for (int i = 0; i < path.size() - 1; i++) {
            if (path.get(i) < path.get(i+1)) {
                isDescending = false;
            }
        }
        return isDescending;
    }

    public Vertex generateUniqueCoordinate(Random xGenerator, Random yGenerator) {
        int x = xGenerator.nextInt(n);
        int y = yGenerator.nextInt(n);
        Vertex v = new Vertex(x, y);
        
        if (vertices.size() > 0) {
            for (int j = 0; j < vertices.size(); j++) {
                if (vertices.get(j).x() == x) {
                    v = generateUniqueCoordinate(xGenerator, yGenerator);
                }
            }
        }
        return v;
    }

    public double getDist(Vertex a, Vertex b) {
        double yDistance = Math.abs(b.y() - a.y());
        double xDistance = Math.abs(b.x() - a.x());
        double distance = Math.sqrt(Math.pow(yDistance, 2) + Math.pow(xDistance, 2));
        return distance;
    }       
	
    public Vertex getVertex(int i) {
	return vertices.get(i);
    }
    public ArrayList<Vertex> getVertices() {
        return vertices;    
    }
    
    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
        
    public String getMatrixString() {
        String matrixString = "";
	for (int i = 0; i < n; i++) {
	    matrixString += "      " + i;
	}
	
	for (int i = 0; i < n; i++)
	{
            matrixString += "\n\n" + i;
	    for (int j = 0; j < n; j++)
	    {
                matrixString += "   " + df.format(adjacencyMatrix[i][j]);
	    }
	}
        return matrixString;
    }
        
    public String getVerticesString(ArrayList<Vertex> verticesToPrint) {
        String verticesString = "";
        for (int i = 0; i < verticesToPrint.size(); i++) {
	    verticesString += "v" + verticesToPrint.get(i).getNumber()
			    + ": (" + verticesToPrint.get(i).x()
			    + "," + verticesToPrint.get(i).y() + ") ";
	}
        verticesString += "\n";
        return verticesString;
    }
    
    public ArrayList<Vertex> sortVertices(ArrayList<Vertex> toSort) {
	
	Vertex[] vertexArray = new Vertex[toSort.size()];
	toSort.toArray(vertexArray);
		
	sort(vertexArray, 0, vertexArray.length);
	
	ArrayList<Vertex> toReturn = new ArrayList<Vertex>(Arrays.asList(vertexArray));
	return toReturn;
    }
    public static void sort(Vertex[] src, int off, int len) {
	
        if (len < 7) {
            for (int i=off; i<len+off; i++)
                for (int j = i; j > off && src[j-1].x() > src[j].x(); j--)
                    swap(src, j, j-1);
            return;
        }

    
        int m = off + (len >> 1);       
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            if (len > 40) {        
                int s = len/8;
                l = med3(src, l,     l+s, l+2*s);
                m = med3(src, m-s,   m,   m+s);
                n = med3(src, n-2*s, n-s, n);
            }
            m = med3(src, l, m, n); 
        }
        int v = src[m].x();

        
        int a = off, b = a, c = off + len - 1, d = c;
        while(true) {
            while (b <= c && src[b].x() <= v) {
                if (src[b].x() == v)
                    swap(src, a++, b);
                b++;
            }
            while (c >= b && src[c].x() >= v) {
                if (src[c].x() == v)
                    swap(src, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(src, b++, c--);
        }

        
        int s, n = off + len;
        s = Math.min(a-off, b-a  );  vecswap(src, off, b-s, s);
        s = Math.min(d-c,   n-d-1);  vecswap(src, b,   n-s, s);

        
        if ((s = b-a) > 1)
            sort(src, off, s);
        if ((s = d-c) > 1)
            sort(src, n-s, s);
    }
    
    private static void swap(Vertex[] src, int a, int b) {
        Vertex t = src[a];
        src[a] = src[b];
        src[b] = t;
    }
    
    private static void vecswap(Vertex[] src, int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(src, a, b);
    }
    
  
    private static int med3(Vertex[] src, int a, int b, int c) {
        return (src[a].x() < src[b].x() ?
                (src[b].x() < src[c].x() ? b : src[a].x() < src[c].x() ? c : a) :
                (src[b].x() > src[c].x() ? b : src[a].x() > src[c].x() ? c : a));
    }
}