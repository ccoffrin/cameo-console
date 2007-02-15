package legasy;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cameocontrol.cameo.control.CameoConsole;
import com.cameocontrol.cameo.control.CameoFade;



public class AtGUI extends JPanel implements ActionListener {

	private JTextField channel;
	private JTextField level;
	private JButton editable;
	private CameoConsole console;
	
	public AtGUI(CameoConsole c) {
		
		super(new GridLayout(1, 1));
		
		console = c;
		
		
		
		channel = new JTextField();
		//deleteCue.setActionCommand("delete cue");
		channel.setBackground(Color.BLACK);
		channel.setForeground(Color.WHITE);
		channel.addActionListener(this);
		
		level = new JTextField();
		//deleteCue.setActionCommand("delete cue");
		level.setBackground(Color.BLACK);
		level.setForeground(Color.WHITE);
		level.addActionListener(this);
		
		editable = new JButton("At");
		editable.setActionCommand("Add cue");
		editable.setBackground(Color.BLACK);
		//editable.setForeground(Color.WHITE);
		editable.addActionListener(this);
		
		this.add(channel);
		this.add(level);
		this.add(editable);
		this.setBackground(Color.BLACK);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (editable.getActionCommand().equals(e.getActionCommand())) {
	//Integerparse as int and cast to sort
			//console.at(Integer.parseInt(channel.getText()), (short)Integer.parseInt(level.getText()));
		}
	}
}
