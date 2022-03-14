package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import draw.Element;
import draw.Line;
import draw.Painter;

public class drawLineMode extends Mode{
	private String lineType = null;
	private Painter painter = new Painter();
	private Point startP = null;
	private List<Element> elements;
	private int Port1 = -1, Port2 = -1;
	private Element e1 = null, e2 = null;
	
	
	public drawLineMode(String lineType) {
		this.lineType = lineType;
	}
	
	public void mousePressed(MouseEvent e) {
		elements = canvas.getElementList();
		/* find which basic object, record its reference and port number */
		startP = findConnectedObj(e.getPoint(), "first");
	}

	public void mouseDragged(MouseEvent e) {
		/* show dragged line */
		if (startP != null) {
			Line line = painter.paintLine(lineType, startP, e.getPoint());
			canvas.dragLine = line;
			canvas.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		Point endP = null;
		if (startP != null) {
			/* find which basic object, record its reference and port number */
			endP = findConnectedObj(e.getPoint(), "second");

			/* if end of line inside the basic object */
			if (endP != null) {
				Line line = painter.paintLine(lineType, startP, endP);
				canvas.addElement(line);

				/* add relative ports to line */
				line.setPorts(e1.getPort(Port1), e2.getPort(Port2));

				/* add line to relative port of two basic object */
				e1.getPort(Port1).addLine(line);
				e2.getPort(Port2).addLine(line);
			}
			// reset
			canvas.dragLine = null;
			canvas.repaint();
			startP = null;
		}
	}

	private Point findConnectedObj(Point p, String target) {
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);

			/* check if or not mouse pressed inside the basic object */
			int portIndex;
			String judgeInside = element.mouselocation(p);
			if (judgeInside != null && judgeInside != "onLine") {
				
				
				/* if shape inside the group */
				if(judgeInside == "insideGroup"){  
					element = element.getSelectedElement();
					portIndex = Integer.parseInt(element.mouselocation(p));
				}
				else
					portIndex = Integer.parseInt(judgeInside);
			
				/* if inside the basic object, get the location of relative port */
				switch (target) {
				case "first":
					e1 = element;
					Port1 = portIndex;
					break;
				case "second":
					e2 = element;
					Port2 = portIndex;
					break;
				}
				Point portLocation = new Point();
				portLocation.setLocation(element.getPort(portIndex).getCenterX(), 
						                 element.getPort(portIndex).getCenterY());
				return portLocation;
			}

		}
		return null;
	}
}
