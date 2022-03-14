package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import draw.Element;
import draw.Line;

public class selectMode extends Mode{
	private List<Element> elements;
	private Point startP = null;
	private String judgeInside = null;
	private Line selectedLine = null;
	
	public void mousePressed(MouseEvent e) {
		startP = e.getPoint();
		elements = canvas.getElementList();
		// reset
		canvas.reset();

		/* find which basic object, record its reference */
		for (int i = elements.size() - 1; i >= 0; i--) {
			Element element = elements.get(i);
			judgeInside = element.mouselocation(e.getPoint());
			if (judgeInside != null) {
				canvas.selectedObj = element;
				break;
			}
		}
		canvas.repaint();
	}

	public void mouseDragged(MouseEvent e) {
		int moveX = e.getX() - startP.x;
		int moveY = e.getY() - startP.y;
		/* object selected */
		if (canvas.selectedObj != null) {
			// move Line object
			if (judgeInside == "insideLine") {
				selectedLine = (Line) canvas.selectedObj;
				selectedLine.resetStartEnd(e.getPoint());
			}
			else {
				canvas.selectedObj.resetPosition(moveX, moveY);
			}
			startP.x = e.getX();
			startP.y = e.getY();
		}

		else {
			// left to right
			if (e.getX() > startP.x)
				canvas.SelectedArea.setBounds(startP.x, startP.y, Math.abs(moveX), Math.abs(moveY));
			// right to left
			else
				canvas.SelectedArea.setBounds(e.getX(), e.getY(), Math.abs(moveX), Math.abs(moveY));
		}
		canvas.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		if (canvas.selectedObj != null) {
			// move Line object
			if (judgeInside == "onLine") {
				selectedLine = (Line) canvas.selectedObj;
				reconnectLine(e.getPoint());
			}
		}
		/* group area selected */
		else {
			canvas.SelectedArea.setSize(Math.abs(e.getX() - startP.x), Math.abs(e.getY() - startP.y));
		}
		canvas.repaint();
	}
	
	private void reconnectLine(Point p) {
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			int portIndex;
			String judgeInside = element.mouselocation(p);
			if (judgeInside != null && judgeInside != "onLine") {
				/* if shape inside the group */
				if (judgeInside == "insideGroup") {
					element = element.getSelectedElement();
					portIndex = Integer.parseInt(element.mouselocation(p));
				}
				else
					portIndex = Integer.parseInt(judgeInside);
				
				selectedLine.resetPorts(selectedLine, element.getPort(portIndex));
				selectedLine.resetPosition();
			}
		}

	}
}
