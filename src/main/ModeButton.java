package main;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

import mode.Mode;

public class ModeButton extends JButton{
	Mode modeType;
	protected ImageIcon icon;
	protected ImageIcon iconUsing;
	
	public ModeButton(String btnName, ImageIcon icon, ImageIcon iconUsing, Mode modeType, ActionListener listener) {
		this.icon = icon;
		this.iconUsing = iconUsing;
		this.modeType = modeType;
		
		setName(btnName);
		setIcon(icon);
		setToolTipText(btnName);
        setBorderPainted(false);
		setFocusable(false);
		setRolloverEnabled(true);
		addActionListener(listener);
	}
}
