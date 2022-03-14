package draw;

import java.awt.Color;
import java.awt.Graphics;

public class Class extends Object{
	public Class(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;
		this.width = 70;
		this.height = 90;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		this.objectName = "Class Name";
		locatePorts();
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x1, y1, width, height);
		
		g.setColor(Color.black);
		g.drawRect(x1, y1, width, height);
		int lineDistance = height / 3;
		g.drawLine(this.x1, this.y1 + lineDistance,
				   this.x2, this.y1 + lineDistance);
		g.drawLine(this.x1, this.y1 + lineDistance*2,
				   this.x2, this.y1 + lineDistance*2);
	
		// find the width for the specified string.
		int stringWidth = g.getFontMetrics(font).stringWidth(objectName);
		double empty = (Math.abs(x1-x2) - stringWidth)/2;
		g.setFont(font);	
		g.drawString(objectName, x1 + (int)empty, y1 + 25);
	}
}
