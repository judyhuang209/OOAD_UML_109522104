package draw;

import java.awt.Point;

public class Painter {
	public Object paintObject(String objType,  Point p) {
		if(objType.equals("class")){
			Object target = new Class(p.x, p.y);
			System.out.println("paint class");
			return target;
		}
		else if(objType.equals("usecase")){
			Object target = new UseCase(p.x, p.y);
			System.out.println("paint usecase");
			return target;
		}
		return null;
	}
	
	public Line paintLine(String lineType, Point P1, Point P2) {
		if(lineType.equals("ass")){
			Line target = new AssLine(P1.x, P1.y, P2.x, P2.y);
			return target;
		}
		else if(lineType.equals("gen")){
			Line target = new GenLine(P1.x, P1.y, P2.x, P2.y);
			return target;
		}
		else if(lineType.equals("com")) {
			Line target = new ComLine(P1.x, P1.y, P2.x, P2.y);
			return target;
		}
		return null;
	}
	
}
