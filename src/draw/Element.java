package draw;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Element {
	protected int x1, x2, y1, y2;
	protected boolean selected = false;
	
	public abstract void draw(Graphics g);
	public void changeName(String name) {};
	
	// get mouse in which location of the element
	public abstract String mouselocation(Point p);
	public abstract void show(Graphics g);
	public PortPoint getPort(int portIndex) {
		return null;
	};
	

	// Group
	public void resetSelectedElement() {};
	public Element getSelectedElement() {
		return null;
	};
	
	public void resetPosition() {}
	public void resetPosition(int X, int Y) {}
	
	
	public Point getP1() {
		Point P1 = new Point(x1, y1);
		return P1;
	}
	
	public Point getP2() {
		Point P2 = new Point(x2, y2);
		return P2;
	}
	
	// get this element is group-selected or not
	public boolean getSelected() {
		return selected;
	}
	
	public void setSelected() {
		selected = true;
	}
	
	public void setUnselected() {
		selected = false;
	}
	
}
