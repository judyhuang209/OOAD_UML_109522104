package draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class GroupElements extends Element {
	private List<Element> elements = new ArrayList<Element>();
	private Rectangle bounds = new Rectangle();
	private Element selectedElement = null;

	public void draw(Graphics g) {
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			element.draw(g);
		}
	}

	public void show(Graphics g) {
		int offset = 10;
		int alpha = 50;
		g.setColor(new Color(0, 0, 255, alpha));
		g.fillRect(bounds.x - offset, bounds.y - offset, bounds.width + offset * 2, bounds.height + offset * 2);
		g.setColor(new Color(0, 0, 255));
		g.drawRect(bounds.x - offset, bounds.y - offset, bounds.width + offset * 2, bounds.height + offset * 2);
		g.setColor(Color.black);
		if (selectedElement != null) {
			selectedElement.show(g);
		}
	}

	public void resetPosition(int moveX, int moveY) {
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			element.resetPosition(moveX, moveY);
		}
		resetBounds(moveX, moveY);
	}

	public String mouselocation(Point p) {
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			String judgeInside = element.mouselocation(p);
			if (judgeInside != null) {
				selectedElement = element;
				return "insideGroup";
			}
		}
		return null;
	}

	public void changeName(String name) {
		selectedElement.changeName(name);
	}

	public void resetSelectedElement() {
		selectedElement = null;
	}
	
	public Element getSelectedElement() {
		return selectedElement;
	}
	
	public void setBounds() {
		Point upLeftP, bottomRightP;
		int Xmin = Integer.MAX_VALUE, Xmax = Integer.MIN_VALUE;
		int Ymin = Integer.MAX_VALUE, Ymax = Integer.MIN_VALUE;

		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			Point P1 = element.getP1();
			Point P2 = element.getP2();
			
			if (P1.x < Xmin)
				Xmin = P1.x;
			if (P2.x > Xmax) 
				Xmax = P2.x;
			if (P1.y < Ymin)
				Ymin = P1.y;
			if (P2.y > Ymax)
				Ymax = P2.y;
		}

		upLeftP = new Point(Xmin, Ymin);
		bottomRightP = new Point(Xmax, Ymax);
		bounds.setBounds(upLeftP.x, upLeftP.y, Math.abs(upLeftP.x - bottomRightP.x),
				Math.abs(upLeftP.y - bottomRightP.y));

		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
	}

	protected void resetBounds(int moveX, int moveY) {
		bounds.setLocation(bounds.x + moveX, bounds.y + moveY);
		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
	}

	public void addElement(Element element) {
		elements.add(element);
	}

	public List<Element> getElementList() {
		return elements;
	}
}
