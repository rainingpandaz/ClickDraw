import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ClickDraw extends JPanel implements MouseListener {
  private int myWindowWidth = 700;
  private int myWindowHeight = 500;
  private ArrayList<Integer> clicks;

  // a) Declaration of an ArrayList of Point objects goes here.
  private ArrayList<Point> points;
  
  public ClickDraw() {
    // a) Initialization of an ArrayList of Point objects goes here.
    JFrame easel = new JFrame();
    easel.setSize(myWindowWidth, myWindowHeight);
    easel.add(this);
    addMouseListener(this);
    easel.setVisible(true);
    points = new ArrayList<Point>();
    clicks = new ArrayList<Integer>();
  }

  public void mouseClicked(MouseEvent event) {
    Point p = new Point(event.getX(), event.getY());
    if (!onAnotherDot(p)) {
      // b) Adding the point to the ArrayList goes here.
      points.add(p);
      clicks.add(0);
    }
    repaint();
  }

  private boolean closeTo(int x1, int y1, int x2, int y2) {
    return Math.abs(x1 - x2) < 3 && Math.abs(y1 - y2) < 3;
  }

  public void paintComponent(Graphics g) {
    // The following 3 lines erase the display, ...
    g.setColor(Color.white);
    g.fillRect(0, 0, myWindowWidth, myWindowHeight);
    g.setColor(Color.black);
    // c) then redraw it (code goes here).
    for(int i = 0; i < points.size(); i++){
      g.setColor(dotColor(clicks.get(i)));
      g.fillOval((int)points.get(i).getX() - 5,(int)points.get(i).getY() - 5, 10, 10);
      g.setColor(Color.black);
      if(i != 0)
         g.drawLine( (int)points.get(i).getX(), (int)points.get(i).getY(), (int)points.get(i-1).getX(), (int)points.get(i-1).getY());
    }
  }

  private boolean onAnotherDot(Point p) {
    // d) This method should return true if they click on another dot
    // (close to another dot). Before it returns true, it should increment
    // the counter for that dot and if the counter is greater than 2 it
    // should clear both ArrayLists
    for(int i = 0; i < points.size(); i++){
      if(closeTo(points.get(i).x, points.get(i).y, p.x, p.y))
         {
  clicks.set(i, clicks.get(i) + 1);
        if(clicks.size() > 2){  // This needs to be clicks.get(i) instead of clicks.size()
          points.clear();
        }
        return true;
         }
      /*
      The closeTo method is not a Point method, and thus it can not be called with a Point. 
      It is a ClickDraw method.  This is the if statement that you need:
      if(closeTo(points.get(i).x, points.get(i).y), p.x, p.y)    
      */  
    }
    return false;
  }

  public void mouseReleased(MouseEvent event) {}

  public void mousePressed(MouseEvent event) {}

  public void mouseEntered(MouseEvent event) {}

  public void mouseExited(MouseEvent event) {}

  public Color dotColor(int x){
	switch(x)  {
	   case 0 : return Color.black;
	   case 1 : return Color.green;
	   case 2 : return Color.red;
	   default : return Color.magenta;
	}
}

  public static void main(String[] args) {
    ClickDraw click = new ClickDraw();
  }
}
