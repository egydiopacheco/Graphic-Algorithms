public class ImageEx extends Image {

	public ImageEx(int w, int h, int r, int g, int b){

		super(w, h, r, g, b);
	}

	public ImageEx(int w, int h){

		super(w, h);
	}
	
	public static double alpha = 0.333333; // Parameter that controls how much the line in Koch curve will be divided by, each iteration

	/* 
	 * Function receives two points coordinates P,Q.
	 * Calculates it's distance through distance between two points formula.
	 * Return it's values.
	 */
	public double lineLength (double px, double py, double qx, double qy) {
		
		double length = Math.sqrt( Math.pow( (qx-px), 2 ) + Math.pow( (qy-py), 2 ) );
		
		return length;
	}

	/* 
	 * Function receives two points coordinates P,Q and a parameter that control the length of PQ.
	 * Create an array of size 2 and store x-coordinates at X[0] and y-coordinates at X[1].
	 * Those points are the intermediary points of a Koch Curve process.
	 * Returns the array.
	 */
	public double[] intermediaryPoint (double px, double py, double qx, double qy, double param) {
	
		double[] X = new double[2];

		X[0] = ((1 - param) * px) + (param * qx);
		X[1] = ((1 - param) * py) + (param * qy);
		
		return X;
	}

	/* 
	 * Koch Curve recursive algorithm, receives two points coordinates P,Q and a integer
	 *that is the limit of how small PQ can be in the recursion process.
	 * M is the median point of PQ, that's why it receveis 0.5 as a parameter.
	 * A is the left side  (close to P) intermediary point, will be reduced in alpha terms.
	 * C is the right side (close to Q) intermediary point, will be reduced in 1-alpha terms.
	 * V and U are intermediary calculations to find B, which is the peak of the koch curve, at each iteration.
	 */
	public void kochCurve(int px, int py, int qx, int qy, int epsilon){

		double pq = lineLength(px,py,qx,qy);

		if (pq < epsilon) { // Stop condition
			drawLine(px,py,qx,qy); 
			return;
		}

		double[] M = intermediaryPoint(px,py,qx,qy,0.5);
		double[] A = intermediaryPoint(px,py,qx,qy,alpha);
		double[] C = intermediaryPoint(px,py,qx,qy,(1-alpha));

		double[] V = new double[2];
		V[0] = qx-px;
		V[1] = qy-py;

		double[] U = new double[2];
		U[0] = (V[1]) * (Math.sqrt(3)/6);
		U[1] = (-V[0]) * (Math.sqrt(3)/6);

		double[] B = new double[2];
		B[0] = M[0] + U[0];
		B[1] = M[1] + U[1];
		
		int ax = (int)Math.round(A[0]);
		int ay = (int)Math.round(A[1]);

		int bx = (int)Math.round(B[0]);
		int by = (int)Math.round(B[1]);

		int cx = (int)Math.round(C[0]);
		int cy = (int)Math.round(C[1]);

		System.out.println("Drawing..wait");
		
		kochCurve(px,py,ax,ay,epsilon); 

		kochCurve(ax,ay,bx,by,epsilon);

		kochCurve(bx,by,cx,cy,epsilon);

		kochCurve(cx,cy,qx,qy,epsilon);
	}

	/*
	 * Recursive region fill. It uses flood-fill algorithm to paint the region.
	 * Receives coordinates of a point P(x,y) and a reference rgb (RGB of P(x,y)) 
	 * Color each pixel until it founds boundaries (different RGB than reference rgb)
	 */
	public void regionFill(int x, int y, int reference_rgb){
		
		if( x < 0 || y < 0 || x >= getWidth() || y >= getHeight() ) return;

		if(getPixel(x,y) == reference_rgb ) {	
			setPixel(x,y);
			System.out.println("Filling..wait");
			regionFill(x+1,y,reference_rgb);
			regionFill(x,y+1,reference_rgb);
			regionFill(x-1,y,reference_rgb);
			regionFill(x,y-1,reference_rgb);
		}

		else return;
	}
}
