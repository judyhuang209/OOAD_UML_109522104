package draw;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class PortPoint extends Rectangle{
	private List<Line> lines = new ArrayList<Line>(); 
	protected int x, y;
	
	public PortPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPort(int x, int y, int portEdge) {
		this.x = x;
		this.y = y;
		
		int portX = x - portEdge / 2;
		int portY = y - portEdge / 2;
		int width = portEdge;
		int height = portEdge;
		setBounds(portX, portY, width, height);
		System.out.println("port.setPort: " + portX + " " + portY);
	}
	
	public void addLine(Line newline) {
		lines.add(newline);
	}
	
	public void delLine(Line byeline) {
		lines.remove(byeline);
	}
	
	public void resetLines() {
		for(int i = 0; i < lines.size(); i++){
			Line line = lines.get(i);
			line.resetPosition();
		}
	}

}
