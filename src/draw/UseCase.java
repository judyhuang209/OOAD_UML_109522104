package draw;

import java.awt.Color;
import java.awt.Graphics;

public class UseCase extends Object{
	public UseCase(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;
		this.width = 100;
		this.height = 69;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		this.objectName = "Use Case Name";
		locatePorts();
	}
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x1, y1, width, height);
		
		g.setColor(Color.black);
		g.drawOval(x1, y1, width, height);
		
		int stringWidth = g.getFontMetrics(font).stringWidth(objectName);
		double empty = (Math.abs(x1-x2) - stringWidth)/2;
		g.setFont(font);	
		g.drawString(objectName, x1 + (int)empty, y1 + 40);
	}
}
