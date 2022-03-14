package draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public abstract class Object extends Element{
	private int portEdge = 10;
	private int portNum = 4;
	protected PortPoint[] ports = new PortPoint[portNum];
	protected int width, height;
	protected String objectName = "Object Name";
	protected Font font = new Font(Font.DIALOG, Font.BOLD, 12);
	
	public abstract void draw(Graphics g);
	
	public void show(Graphics g) {
		for(PortPoint port : ports) {
			g.setColor(Color.red);
			g.fillRect(port.x, port.y, port.width, port.height);
		}
	}
	
	public PortPoint getPort(int index) {
		return ports[index];
	}
	
	@Override
	public String mouselocation (Point p) {
		Point center = new Point();
		center.x = (x1 + x2) / 2;
		center.y = (y1 + y2) / 2;
		Point[] points = { new Point(x1, y1), new Point(x2, y1), new Point(x2, y2), new Point(x1, y2) };
		
		for (int i = 0; i < points.length; i++) {
			Polygon t = new Polygon();
			// (0,1,center) (1,2,center) (2,3,center) (3,0,center)
			int secondIndex = ((i + 1) % 4);
			t.addPoint(points[i].x, points[i].y);
			t.addPoint(points[secondIndex].x, points[secondIndex].y);
			t.addPoint(center.x, center.y);

			if (t.contains(p)) {
				return Integer.toString(i);
			}
		}
		return null;
	}
	
	protected void locatePorts() {
		int[] Xs = {(this.x1 + this.x2)/2, this.x2, 
				    (this.x1 + this.x2)/2, this.x1};
		int[] Ys = {this.y1, (this.y1 + this.y2)/2,
				    this.y2, (this.y1 + this.y2)/2};
		for(int i = 0; i < portNum; i++) {
			PortPoint port = new PortPoint(Xs[i], Ys[i]);
			port.setPort(Xs[i], Ys[i], portEdge/2);
			ports[i] = port;
		}
	}
	
	@Override
	public void changeName(String name){
		this.objectName = name;
	}
	
	@Override
	public void resetPosition(int moveX, int moveY) {
		int x1 = this.x1 + moveX;
		int y1 = this.y1 + moveY;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		int[] Xs = {(this.x1 + this.x2)/2, this.x2, 
			    (this.x1 + this.x2)/2, this.x1};
		int[] Ys = {this.y1, (this.y1 + this.y2)/2,
			    this.y2, (this.y1 + this.y2)/2};
		for(int i = 0; i < portNum; i++) {
			// System.out.println("object.setPort: " + Xs[i] + " " + Ys[i]);
			ports[i].setPort(Xs[i], Ys[i], portEdge/2);
			ports[i].resetLines();
		}
	}
	
}
