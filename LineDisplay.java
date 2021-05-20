package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

import cont.JOP;
import mod.APLinesWorld;
import mod.Line;

/**
 * This class, Line Display, creates the GUI and manages button actions.
 * It creates the JFrame, JPanel, and buttons.
 * It also consists of an ArrayList of all the Lines created.
 */
public class LineDisplay implements ActionListener{
	
	private ArrayList<Line> _lst;
	private Line _l;
	private APLinesWorld _ap;
	private JFrame _fra, _f;
	private JPanel _p1, _p2;
	private JTabbedPane _pane;
	private JButton _add1, _add2, _edit, _onLine, _intersect, _t;
	private JTextPane _jtp1, _jtp2;
	private JTable _table;
	private JScrollPane _scroll;
	
	public LineDisplay() {
		_lst = new ArrayList<Line>();
		_ap = new APLinesWorld(_lst);
		_l = new Line(0,0,0);
		intro();
		makeButton();
		makeJTextArea();
		makePanel();
		makeTabPane();
		makeFrame();
	}
	
	public void intro() {
		JOP.msg("WELCOME TO THE COLLEGEBOARD'S LINE CALCULATOR!"
				+ "\n\n*Add a line with A, B, and C"
				+ "\n\n*Add a line with slope and y-intercept"
				+ "\n\n*Edit an existing line"
				+ "\n\n*Check if a point is above, below, or on a line"
				+ "\n\n*See the point where two lines intersect"
				+ "\n\n*See all data of existing lines (A, B, C, slope, Y-Intercept, X-Intercept)");
	}
	
	//creates frame
	public void makeFrame() {
		_fra = new JFrame("AP Line");
		_fra.setLayout(new BorderLayout());
		_fra.setResizable(false);
		_fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_fra.setSize(550, 550);
		_fra.setLocationRelativeTo(null);
		_fra.add(_pane);
		validateFrame();
	}
	
	//creates panel
	private void makePanel() {
		_p1 = new JPanel();
		_p1.setLayout(new FlowLayout());
		_p1.setBackground(Color.DARK_GRAY);
		_p1.add(_add1);
		_p1.add(_add2);
		_p1.add(_edit);
		_p1.add(_t);
		_p1.add(_jtp1);
		
		_p2 = new JPanel();
		_p2.setLayout(new FlowLayout());
		_p2.setBackground(Color.DARK_GRAY);
		_p2.add(_onLine);
		_p2.add(_intersect);
		_p2.add(_jtp2);
		
		validatePanel();
	}
	
	//makes tabs 
	private void makeTabPane() {
		_pane = new JTabbedPane();
		_pane.add(_p1);
		_pane.add(_p2);
		
		_pane.add("Add/Edit Line", _p1);
		_pane.add("Compare my Line", _p2);
		
		_pane.setFont(_pane.getFont().deriveFont(18.0f));
	}
	
	//makes buttons
	private void makeButton() {
		_add1 = new JButton("Add A Line with A, B & C (Ax + By + C)");
		_add2 = new JButton("Add A Line with Slope & Y-Intercept (y = mx + b)");
		_edit = new JButton("Edit A Line");
		_onLine = new JButton("Is a point above, below, or on the line?");
		_intersect = new JButton("Find point of intersection between 2 lines");
		_t = new JButton("Click to See Line's Data");
		
		_add1.setFont(_add1.getFont().deriveFont(18.0f));
		_add2.setFont(_add1.getFont().deriveFont(18.0f));
		_edit.setFont(_edit.getFont().deriveFont(18.0f));
		_onLine.setFont(_add1.getFont().deriveFont(18.0f));
		_intersect.setFont(_edit.getFont().deriveFont(18.0f));
		_t.setFont(_t.getFont().deriveFont(18.0f));
		
		_add1.setPreferredSize(new Dimension(550, 35));
		_add2.setPreferredSize(new Dimension(550, 35));
		_edit.setPreferredSize(new Dimension(550, 35));
		_onLine.setPreferredSize(new Dimension(550, 40));
		_intersect.setPreferredSize(new Dimension(550, 40));
		_t.setPreferredSize(new Dimension(550, 40));
		
		_add1.setBackground(Color.orange);
		_add2.setBackground(Color.orange);
		_edit.setBackground(Color.orange);
		_onLine.setBackground(Color.orange);
		_intersect.setBackground(Color.orange);
		_t.setBackground(Color.orange);
		
		_add1.addActionListener(this);
		_add2.addActionListener(this);
		_edit.addActionListener(this);
		_onLine.addActionListener(this);
		_intersect.addActionListener(this);
		_t.addActionListener(this);
	}
	
	//makes text area to see intersection data
	private void makeJTextArea() {
		_jtp1 = new JTextPane();
		_jtp1.setText("List of All My Lines:\n");
		_jtp1.setBounds(200, 200, 350, 350);
		_jtp1.setEditable(false);
		_jtp1.setFont(_jtp1.getFont().deriveFont(18.0f));
		
		_jtp2 = new JTextPane();
		_jtp2.setText("Intersections of lines and points:\n");
		_jtp2.setBounds(200, 200, 350, 350);
		_jtp2.setEditable(false);
		_jtp2.setFont(_jtp2.getFont().deriveFont(18.0f));
	}
	
	//makes table to see all the data of the lines
	private JFrame makeTable() {
		_f = new JFrame();
		String[][] r = new String[_lst.size()][7];
		String[] c = {"#", "A", "B", "C", "Slope", "Y-Int", "X-Int"};
		for(int i = 0; i < _lst.size(); i++) {
			r[i][0]= String.valueOf(i+1);
			r[i][1]= String.valueOf(_lst.get(i).getA());
			r[i][2]= String.valueOf(_lst.get(i).getB());
			r[i][3]= String.valueOf(_lst.get(i).getC());
			r[i][4]= String.valueOf(_lst.get(i).getSlope());
			r[i][5]= String.valueOf(_lst.get(i).getYInt());
			r[i][6]= String.valueOf(_lst.get(i).getXInt());
		}
		_table = new JTable(r, c);
		_scroll = new JScrollPane(_table);
		_table.setVisible(true);
		_scroll.setVisible(true);
		_f.add(_scroll);          
		_f.setSize(400,400);    
		_f.setVisible(true);
		_f.setLocationRelativeTo(null);
		return _f;
		
	}
	
	private void validatePanel() {
		_p1.repaint();
		_p1.validate();
		_p1.setVisible(true);
		
		_p2.repaint();
		_p2.validate();
		_p2.setVisible(true);
	
	}
	
	private void validateFrame() {
		_fra.repaint();
		_fra.validate();
		_fra.setVisible(true);
	}

	//controls the button actions
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == _t) {
			makeTable();
		}
		if(e.getSource() == _add1) {
			String a = JOP.in("Enter A: (Ax + By + C = 0)");
			String b = JOP.in("Enter B: (Ax + By + C = 0)");
			String c = JOP.in("Enter C: (Ax + By + C = 0)");
			double a1 = Double.parseDouble(a);
			double b1 = Double.parseDouble(b);
			double c1 = Double.parseDouble(c);
			Line l = new Line(a1, b1, c1);
			_lst.add(l);
			_jtp1.setText(_ap.lineList());
			JOP.msg("YOU ADDED A LINE!\n" + 
					"A = " + a1 + 
					"\nB = " + b1 + 
					"\nC = " + c1 + 
					"\nSlope = " + l.getSlope() +
					"\nY-Intercept = " + l.getYInt() +
					"\nX-Intercept = " + l.getXInt());
		}
		if(e.getSource() == _add2) {
			String a = JOP.in("Enter the slope, m: (y = mx + b)");
			String b = JOP.in("Enter the y-intercept, b: (y = mx + b)");
			double a1 = Double.parseDouble(a);
			double b1 = Double.parseDouble(b);
			Line l = new Line(a1, b1);
			_lst.add(l);
			_jtp1.setText(_ap.lineList());
			JOP.msg("YOU ADDED A LINE!\n" + 
					"A = " + l.getA() + 
					"\nB = " + l.getB() + 
					"\nC = " + l.getC() + 
					"\nSlope = " + a1 +
					"\nY-Intercept = " + b1 +
					"\nX-Intercept = " + l.getXInt());
		}
		if(e.getSource() == _edit) {
			String s = JOP.in("Which line would you like to edit?\n" + 
					_ap.lineList() + "\n\nEnter the number of the line.");
			
			String ch = JOP.in("(Enter 1 or 2) Edit the line with: \n" +
			                     "1) A, B, and C\n" 
								+ "2) Slope and Y-Intercept");
			if(ch.equals("1")) {	
				String a = JOP.in("Enter A: (Ax + By + C = 0)");
				String b = JOP.in("Enter B: (Ax + By + C = 0)");
				String c = JOP.in("Enter C: (Ax + By + C = 0)");
				double a1 = Double.parseDouble(a);
				double b1 = Double.parseDouble(b);
				double c1 = Double.parseDouble(c);
				
				Line l = new Line(a1, b1, c1);
				
				_lst.set(Integer.parseInt(s) - 1, l);
				_jtp1.setText(_ap.lineList());
			}
			else if(ch.equals("2")) {
				String a = JOP.in("Enter the slope, m: (y = mx + b)");
				String b = JOP.in("Enter the y-intercept, b: (y = mx + b)");
				double a1 = Double.parseDouble(a);
				double b1 = Double.parseDouble(b);
				Line l = new Line(a1, b1);
				_lst.set(Integer.parseInt(s) - 1, l);
				_jtp1.setText(_ap.lineList());
			}
		
		}
		if(e.getSource() == _onLine) {
			if(_lst.size() == 0) {
				JOP.msg("Sorry, you have made no lines yet. Add a line and try again.");
			}
			else {
				String output = "";
				String s = JOP.in("Pick a line to check if a point intersects with it:"
						+ "\n" + _ap.lineList() + "\n\nEnter the number of the line.");
				String x1 = JOP.in("Enter the x-value of the point.");
				String y1 = JOP.in("Enter the y-value of the point.");
				double x = Double.parseDouble(x1);
				double y =  Double.parseDouble(y1);
				
				int i = Integer.parseInt(s) - 1;
				Line l = _lst.get(i);
				output = _l.aboveOrBelow(x, y, l);
				JOP.msg(output);
				_jtp2.setText(_jtp2.getText() + "*" + output + "\n");
			}
		}
		if(e.getSource() == _intersect) {
			if(_lst.size() == 0) {
				JOP.msg("Sorry, you have made no lines yet. Add two line and try again.");
			}
			else if(_lst.size() == 1){
				JOP.msg("Sorry, you only have one line. Add another line and try again.");
			}
			else {
				String s = JOP.in("Enter the number of the 1ST line you want to check for intersection:\n"+
								_ap.lineList());
				String t = JOP.in("Enter the number of the 2ND line you want to check for intersection:\n"+
						_ap.lineList());
				String b = _l.checkIntersection(_lst.get(Integer.parseInt(s) - 1), 
						_lst.get(Integer.parseInt(t) - 1));
				
				JOP.msg(b);
				_jtp2.setText(_jtp2.getText() + "*" +b+ "\n");
			}
		}
	}
	
}
