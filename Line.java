package mod;

/**
 * This class, Line, represents a Line. 
 * A line has: 
 *   - an 'a' value (ax + by + c = 0)
 *   - 'b' value (ax + by + c = 0)
 *   - 'c' value (ax + by + c = 0)
 *   - y-intercept
 *   - slope
 *   - x-intercept
 *   
 * The class also checks the relationships between lines and points
 * (such as intersection points).
 * @author diyaa
 *
 */
public class Line {

	private double _a;
	private double _b;
	private double _c;
	private double _yint;
	private double _slope;
	private double _xint;
	
	public double getA() { return _a; }
	public double getB() { return _b; }
	public double getC() { return _c; }

	public Line(double a1, double b1, double c1) {
		_a = a1;
	    _b = b1;
	    _c = c1;
	    _slope = 0.0;
	}
	
	public Line(double slope, double yint) {
		_slope = slope;
		_yint = yint;
		_a = -slope;
		_b = 1;
		_c = -yint;
	}
	
	//gets y intercept
	public double getYInt() {
		_yint = round(-_c/_b);
		return _yint;
	}

	//gets slope (-a/b)
	public double getSlope() {
		_slope = round(-_a/_b);
	    return _slope;
	}

	//gets x-intercept
	public double getXInt() {
		_xint = round(-_c/_a);
		return _xint;
	}
	
	//returns true if point is on line
	public boolean onLine(int x, int y, Line l) {
		return (0 == round((l.getA() * x) + (l.getB() * y) + l.getC()));
	}
	
	//returns true if point is above line, false if below line
	public String aboveOrBelow(double x, double y, Line l) {
		if(((l.getA()*x) + (l.getB()*y) + l.getC()) > 0) {
			return "The point (" + x + ", " + y + ") is above the line " +
		          "(" + getEqu(l) + ")";
		}
		if(((l.getA()*x) + (l.getB()*y) + l.getC()) < 0) {
			return "The point (" + x + ", " + y + ") is below the line " +
					 "(" + getEqu(l) + ")";
		}
		return "The point (" + x + ", " + y + ") is on the line " +
		 "(" + getEqu(l) + ")";
	}
	//returns the point, if any, at which two points intersect
	public String checkIntersection(Line one, Line two) {
		double a = one.getA();
		double b = one.getB();
		double e = -one.getC();
		double c = two.getA();
		double d = two.getB();
		double f = -two.getC();
		
		if(a == c && b == d) {
			if(e == f) {
				return getEqu(one) + " and " + getEqu(two) + "\n"
						+ "are the same line. They have infinite "
						+ "intersection points.";
			}
			return getEqu(one) + " and " + getEqu(two) + "\n" + "are parallel. "
					+ "They will never intersect.";
		}
		
		double temp = ((a)*(d) - (b)*(c));
		double x = round(((d*e) - (b*f)) / temp);
		double y = round(((a*f) - (c*e)) / temp);
		
		String s = "The point of intersection between \n" + 
				getEqu(one) + " and " + 
				getEqu(two) + " is \n" + 
				"(" + x + ", " + y + ")";
		return s;
				
	}
	
	//rounds the number to two decimal places
	private double round(double n) {
		double scale = Math.pow(10, 2);
	    double d = Math.round(n * scale) / scale;
		return d;
	}
	
	//returns the equation of a line
	public String getEqu(Line l) {
		return l.getA() + "x + " + l.getB() + "y + "+ l.getC() + " = 0";
	}
	 
	

}

