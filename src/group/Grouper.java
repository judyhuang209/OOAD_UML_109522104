package group;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import draw.Element;
import draw.GroupElements;
import main.Canvas;

public class Grouper {
	protected Canvas canvas = Canvas.getInstance(); 
	
	public void group() {
		GroupElements group = new GroupElements();
		for (int i = 0; i < canvas.elements.size(); i++) {
			Element element = canvas.elements.get(i);
			if (element.getSelected()) {
				group.addElement(element);
				canvas.elements.remove(i);
				i--;
			}
		}
		group.setBounds();
		canvas.elements.add(group);
	}
	
	public void ungroup() {
		GroupElements group = (GroupElements) canvas.selectedObj;
		List<Element> groupElements = group.getElementList();
		for(int i = 0; i < groupElements.size(); i++){
			Element element = groupElements.get(i);
			canvas.elements.add(element);
		}
		canvas.elements.remove(canvas.selectedObj);
	}
}
