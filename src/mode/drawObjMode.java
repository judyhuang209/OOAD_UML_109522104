package mode;

import java.awt.event.MouseEvent;

import draw.Element;
import draw.Painter;

public class drawObjMode extends Mode{
	private String objType = null;
	private Painter painter = new Painter();
	
	public drawObjMode(String objType) {
		this.objType = objType;
	}
	
	public void mousePressed(MouseEvent e) {
		Element obj = painter.paintObject(objType, e.getPoint());
		canvas.addElement(obj);
		canvas.repaint();
		System.out.println("drawobj mode clicked");
	}
}
