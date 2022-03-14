package main;

import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import mode.*;

public class ToolBar extends JToolBar{
	private static int toolNum = 6;
	// use list could be better :(
	protected ModeButton[] buttons = new ModeButton[toolNum];
	private Canvas canvas;
	private Mode modeType;
	
	
	public ToolBar() {
		init();
	}
	
	public void init() {
		canvas = Canvas.getInstance();
		
		setLayout(new GridLayout(toolNum, 1, 2, 2));
		
		ImageIcon selectIcon = new ImageIcon("icon/select.png");
		ImageIcon selectUsingIcon = new ImageIcon("icon/select_using.png");
		ModeButton selectBtn = new ModeButton("Select", selectIcon, selectUsingIcon,
												new selectMode(),
												new ToolBarListener());
		add(selectBtn);
		this.buttons[0] = selectBtn;
		
		ImageIcon associationIcon = new ImageIcon("icon/association_line.png");
		ImageIcon associationUsingIcon = new ImageIcon("icon/association_line_using.png");
		ModeButton associationBtn = new ModeButton("Association Line", associationIcon, associationUsingIcon,
													new drawLineMode("ass"),
													new ToolBarListener());
		add(associationBtn);
		this.buttons[1] = associationBtn;
		
		ImageIcon generalizationIcon = new ImageIcon("icon/generalization_line.png");
		ImageIcon generalizationUsingIcon = new ImageIcon("icon/generalization_line_using.png");
		ModeButton generalizationBtn = new ModeButton("Generalization Line", generalizationIcon, generalizationUsingIcon,
														new drawLineMode("gen"),
														new ToolBarListener());
		add(generalizationBtn);
		this.buttons[2] = generalizationBtn;
		
		ImageIcon compositionIcon = new ImageIcon("icon/composition_line.png");
		ImageIcon compositionUsingIcon = new ImageIcon("icon/composition_line_using.png");
		ModeButton compositionBtn = new ModeButton("Composition Line", compositionIcon, compositionUsingIcon,
													new drawLineMode("com"),
													new ToolBarListener());
		add(compositionBtn);
		this.buttons[3] = compositionBtn;
		
		ImageIcon classIcon = new ImageIcon("icon/class.png");
		ImageIcon classUsingIcon = new ImageIcon("icon/class_using.png");
		ModeButton classBtn = new ModeButton("Class", classIcon, classUsingIcon, 
											new drawObjMode("class"),
											new ToolBarListener());
		add(classBtn);
		this.buttons[4] = classBtn;
		
		ImageIcon usecaseIcon = new ImageIcon("icon/use_case.png");
		ImageIcon usecaseUsingIcon = new ImageIcon("icon/use_case_using.png");
		ModeButton usecaseBtn = new ModeButton("Use Case", usecaseIcon, usecaseUsingIcon, 
												new drawObjMode("usecase"), 
												new ToolBarListener());
		add(usecaseBtn);
		this.buttons[5] = usecaseBtn;
	}
	
	private class ToolBarListener implements ActionListener{
		private ModeButton targetBtn = null;
		public void actionPerformed (ActionEvent e) {
			targetBtn = (ModeButton) e.getSource();
			targetBtn.setIcon(targetBtn.getIcon() == targetBtn.icon ? 
								targetBtn.iconUsing : targetBtn.icon);
			System.out.println(targetBtn.getName());
			
			for (ModeButton btn: buttons){
			    if (btn != e.getSource())
			    	btn.setIcon(btn.icon);
			}
			
			canvas.modeType = targetBtn.modeType;
			canvas.setMode();
			// canvas.reset();
			canvas.repaint();
		}
	}
	

}
