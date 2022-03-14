package main;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;
import java.util.ArrayList;
import java.util.List;

import mode.Mode;
import draw.Line;
import draw.Element;

public class Canvas extends JPanel{
	private static Canvas instance = null;
	private EventListener listener;
	protected Mode modeType;
	public List<Element> elements = new ArrayList<Element>();
	public Element selectedObj = null;
	public Rectangle SelectedArea = new Rectangle();
	
	public Line dragLine = null;
	
	private Canvas() {
		// setLayout(new FlowLayout());
	}
	
	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}
	
	public void setMode() {
		// get current editing mode
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
		listener = modeType;
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	public void reset() {
		if (selectedObj != null) {
			selectedObj = null;
		}
		SelectedArea.setBounds(0, 0, 0, 0);// reset canvas
	}
	
	public void show() {
		// show objects
	}
	
	public void addElement(Element element) {
		elements.add(element);
	}
	
	public List<Element> getElementList(){
		return this.elements;
	}
	
	public void changeObjName(String name) {
		if(selectedObj != null){
			selectedObj.changeName(name);
			repaint();
		}
	}

	private boolean checkSelectedArea(Element element) {
		Point P1 = new Point(element.getP1());
		Point P2 = new Point(element.getP2());
		if (SelectedArea.contains(P1) && SelectedArea.contains(P2)) {
			return true;
		}
		return false;
	}
	
	public void paint(Graphics g) {
		/* set canvas area */
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
		/* set painting color */
		g.setColor(Color.black);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		
		// paint all elements
		for (int i = elements.size() - 1; i >= 0; i--) {
			Element element = elements.get(i);
			element.draw(g);
			element.setUnselected();
			// System.out.println("canvas print");
			if (!SelectedArea.isEmpty()) {
				if (checkSelectedArea(element)) {
					// show ports of selected objects
					element.show(g);
					element.setSelected();
				}
			}
		}

		// paint dragged line
		if (dragLine != null) {
			dragLine.draw(g);
		}
		
		// show ports when object is selected
		if (this.selectedObj != null) {
			selectedObj.show(g);
		}
		
		// paint area of group selection
		if (!SelectedArea.isEmpty()) {
			int alpha = 50;
			g.setColor(new Color(0, 0, 255, alpha));
			g.fillRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
			g.setColor(new Color(0, 0, 255));
			g.drawRect(SelectedArea.x, SelectedArea.y, SelectedArea.width, SelectedArea.height);
		}
	}

}
