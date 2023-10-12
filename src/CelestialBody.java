

/**
 * Data Structures and Algorithms project
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 * If you add code here, add yourself as @author below
 *@author kk416
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete constructor
		this.myXPos = xp;
		this.myYPos = yp;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = mass;
		this.myFileName = filename;
	}

	/**
	 *
	 * @return
	 */
	public double getX() {
		// TODO: complete method
		return myXPos;
	}

	/**
	 *
	 * @return
	 */
	public double getY() {
		// TODO: complete method
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		return myYVel;
	}

	/**
	 *
	 * @return
	 */
	public double getMass() {
		return myMass;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double dist = Math.sqrt(Math.pow(this.myXPos - b.myXPos, 2) + Math.pow(this.myYPos - b.myYPos, 2));

		return dist;
	}

	public double calcForceExertedBy(CelestialBody b) {
		double F = (0.0000000000667*(b.myMass * this.myMass))/(calcDistance(b)*calcDistance(b));
		return F;
	}

	public double calcForceExertedByX(CelestialBody b) {
		double dx = b.myXPos - this.myXPos;
		double Fx = calcForceExertedBy(b)*dx/calcDistance(b);
		return Fx;
	}
	public double calcForceExertedByY(CelestialBody b) {
		double dy = b.myYPos - this.myYPos;
		double Fy = calcForceExertedBy(b)*dy/calcDistance(b);
		return Fy;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				sum += calcForceExertedByX(b);
			}
			else {
				sum += 0.0;
			} 
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				sum += calcForceExertedByY(b);
			}
			else {
				sum += 0.0;
			} 
		}
		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
						double ax = xforce/ this.getMass();
						double ay = yforce /this.getMass();
						double nvx = deltaT*ax + (this.getXVel());
						double nvy = deltaT*ay + (this.getYVel());
						double nx = this.getX() + deltaT*nvx;
						double ny = this.getY() + deltaT*nvy;
						myXPos = nx;
						myYPos = ny;
						myXVel = nvx;
						myYVel = nvy;

	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
