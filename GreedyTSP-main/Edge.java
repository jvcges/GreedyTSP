import java.text.*;

public class Edge {
    
    private int left;
    private int right;
    private double weight;
    DecimalFormat df = new DecimalFormat("0.00");
    
    public Edge(int _left, int _right, double _weight){
        left = _left;
        right = _right;
        weight = _weight;
    }
    
    public int getLeft() {
        return left;
    }
    
    public int getRight() {
        return right;
    }
    
    public double getDist() {
        return weight;
    }
    
    public String toString() {
        return (String.valueOf(left) + " " + String.valueOf(right) + " distância = " + df.format(weight));
    }
    
    public void setLeft(int _left) {
        left = _left;
    }
    
    public void setRight(int _right) {
        right = _right;
    }
    
    public void setCoords(int _left, int _right) {
        setLeft(_left);
        setRight(_right);
    }
    
    public void setDist(double _weight) {
        weight = _weight;
    }
}