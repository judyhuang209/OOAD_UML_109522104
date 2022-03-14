package draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;

public abstract class Line extends Element{
	protected PortPoint[] ports = new PortPoint[2];
	public abstract void draw(Graphics g);
	private String startOrEnd = null;
	
	public PortPoint getPort(int portIndex){
		return null;
	}
	
	public void setPorts(PortPoint port1, PortPoint port2) {
		this.ports[0] = port1;
		this.ports[1] = port2;
	}
	
	public void resetPosition(){
		this.x1 = (int) ports[0].x;
		this.y1 = (int) ports[0].y;
		this.x2 = (int) ports[1].x;
		this.y2 = (int) ports[1].y;
		System.out.println("line.ResetPos: " + x1 + " " + x2);
	}
	
	public void resetStartEnd(Point p) {
		if(startOrEnd == "start"){
			this.x1 = p.x;
			this.y1 = p.y;
		}
		else if(startOrEnd == "end") {
			this.x2 = p.x;
			this.y2 = p.y;
		}
	}
	
	public void resetPorts(Line l, PortPoint port) {
		port.addLine(l);
		if(startOrEnd == "start") {
			this.ports[0].delLine(l);
			this.ports[0] = port;
		}
		else if(startOrEnd == "end") {
			this.ports[1].delLine(l);
			this.ports[1] = port;
		}
	}
	
	public void show(Graphics g) {
		g.setColor(Color.black);
		this.draw(g);
		g.setColor(Color.white);
	}
	
	private double getDistance(Point p) {
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		return line.ptLineDist(p.getX(), p.getY());
	}
	
	@Override
	public String mouselocation(Point p) {
		int tolerance = 5;
		if(getDistance(p) < tolerance) {
			double distToStart = Math.sqrt(Math.pow((p.x - x1),2) + Math.pow((p.y - y1), 2));
			double distToEnd = Math.sqrt(Math.pow((p.x - x2),2) + Math.pow((p.y - y2), 2));
			if(distToStart < distToEnd)
				startOrEnd = "start";
			else
				startOrEnd = "end";
			return "onLine";
		}
		else
			return null;
	}
	
}
