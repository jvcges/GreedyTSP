public class Vertex {
    
  private final int x;
  private final int y;
  private int number;
  private double dist;
  
  public Vertex(int _x, int _y){
      x = _x;
      y = _y;
      number = -1;
      dist = -1;
  }
  
  public int x() {
      return x;
  }
  
  public int y() {
      return y;
  }
  
  public void setNumber(int _number) {
      number = _number;
  }
  
  public int getNumber() {
      return number;
  }
  
  public void setDist(double _dist) {
      dist = _dist;
  }
  
  public double getDist() {
      return dist;
  }
}