package mod;

import java.util.ArrayList;

/**
 * This class, APLinesWorld, is mainly to create a list of all the 
 * lines that have been created in the program.
 */
public class APLinesWorld {
	
	private ArrayList<Line> _lst;
	
	public APLinesWorld(ArrayList<Line> arr) {
		_lst = arr;
	}
	
	public String lineList() {
		String s = "List of All My Lines:\n";
		for(int i = 0; i < _lst.size(); i++) {
			s += (i+1) + ") " + _lst.get(i).getA() + "x + " 
					+ _lst.get(i).getB()
					+ "y + " + _lst.get(i).getC() + " = 0";
			s += "   --->   (y = " + _lst.get(i).getSlope() + "x + " + 
					_lst.get(i).getYInt() + ")\n";
		}
		return s;
	}
}
