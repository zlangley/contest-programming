import java.util.*;
import java.math.BigInteger;

class BigPoint {
	BigInteger x, y;
	
	public BigPoint() { x = y = BigInteger.ZERO; }
	
	public BigInteger mDist(BigPoint p) {
		BigInteger dx = x.subtract(p.x);
		BigInteger dy = y.subtract(p.y);
		return dx.abs().add(dy.abs());
	}
	
	public boolean equals(Object other) {
		if (other == null || !(other instanceof BigPoint)) { return false; }
		BigPoint p = (BigPoint)other;
		return (p.x.equals(x) && p.y.equals(y));
	}
	
	public BigPoint clone() {
		BigPoint p = new BigPoint();
		p.x = x; p.y = y;
		return p;
	}
}

class Result {
	BigPoint delta;
	int dir;
	BigInteger pxpy, nxpy, nxny, pxny;
	boolean loop;
	boolean inf;
	
	public Result() {
		delta = new BigPoint();
		dir = 0;
		pxpy = nxpy = nxny = pxny = BigInteger.ZERO;
		loop = false;
		inf = false;
	}
}

class Func {
	//static int depth = -1;
	
	String[] tokens;
	Func[] functions;
	BigPoint[] running;
	Result[] cache;
	
	Func(String[] tokens, Func[] functions) {
		this.tokens = tokens;
		this.functions = functions;
		running = new BigPoint[4];
		cache = new Result[4];
	}
	
	public Result eval(int dir, BigPoint start) {
		int in_dir = dir;
		
		if (cache[in_dir] != null) {
			return cache[in_dir];
		}
		
		if (running[in_dir] != null) {
			// loop detected
			Result r = new Result();
			r.loop = true;
			r.inf = !(running[in_dir].equals(start));
			return r;
		}
		
		running[in_dir] = start;
		//depth++;
		//String indent = new String(new char[depth*4]).replace('\0', ' ');
		
		BigPoint delta = new BigPoint();
		BigInteger pxpy, nxpy, nxny, pxny;
		pxpy = nxpy = nxny = pxny = BigInteger.ZERO;
		boolean inf = false;
		boolean loop = false;
		for (String t : tokens) {
			//System.out.println(dir);
			//System.out.println("" + delta.x + ", " + delta.y);
			//System.out.println(indent + t);
			
			if (t.equals("GO")) {
				BigPoint d = delta;
				switch (dir) {
					case 0: d.x = d.x.add(BigInteger.ONE);		break;	// right
					case 1: d.y = d.y.subtract(BigInteger.ONE);	break;	// down
					case 2: d.x = d.x.subtract(BigInteger.ONE);	break;	// left
					case 3: d.y = d.y.add(BigInteger.ONE);		break;	// up
				}
				pxpy = pxpy.max( d.x.add(d.y)			);
				nxpy = nxpy.max( d.y.subtract(d.x)		);
				nxny = nxny.max( d.x.add(d.y).negate()	);
				pxny = pxny.max( d.x.subtract(d.y)		);
			}
			else if (t.equals("LEFT")) {
				// turn left
				dir = (dir + 3) % 4;
			}
			else if (t.equals("RIGHT")) {
				// turn right
				dir = (dir + 1) % 4;
			}
			else {
				BigPoint d = delta;
				// eval the next function
				int n = Integer.parseInt(t.substring(1))-1;
				BigPoint f_start = start.clone();
				f_start.x = f_start.x.add(d.x);
				f_start.y = f_start.y.add(d.y);
				Result r = functions[n].eval(dir, f_start);
				
				pxpy = pxpy.max( r.pxpy.add(d.x.add(d.y))			);
				nxpy = nxpy.max( r.nxpy.add(d.y.subtract(d.x))		);
				nxny = nxny.max( r.nxny.add(d.x.add(d.y).negate())	);
				pxny = pxny.max( r.pxny.add(d.x.subtract(d.y))		);
				dir = r.dir;
				d.x = d.x.add(r.delta.x);
				d.y = d.y.add(r.delta.y);
				loop = r.loop;
				inf = r.inf;
				
				if (loop) { break; }
			}
		}
		
		//depth--;
		running[in_dir] = null;
		
		Result r = new Result();
		r.dir = dir;
		r.delta = delta;
		r.pxpy = pxpy;
		r.nxpy = nxpy;
		r.nxny = nxny;
		r.pxny = pxny;
		r.loop = loop;
		r.inf = inf;
		//System.out.println(indent + pxpy + " " + nxpy + " " + nxny + " " + pxny);
		cache[in_dir] = r;
		return r;
	}
}

class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// read number of functions
		int count = scan.nextInt();
		if (count == 0) {
			System.out.println(0);
			System.exit(0);
		}

		// create the function array
		Func[] functions = new Func[count];
		
		// read all functions
		for (int i=0; i<count; i++) {
			int t = scan.nextInt();
			String[] tokens = new String[t];
			for (int j=0; j<t; j++) { tokens[j] = scan.next(); }
			functions[i] = new Func(tokens, functions);
		}
		
		// print all functions
		//for (int i=0; i<functions.length; i++) {
		//	Func f = functions[i];
		//	System.out.println("F" + (i+1) + ": " + Arrays.toString(f.tokens));
		//}
		
		// evaluate (starting to the right)
		Result r = functions[0].eval(0, new BigPoint());
		if (r.inf) {
			System.out.println("Infinity");
		}
		else {
			BigInteger maxDist = r.pxpy.max(r.nxpy.max(r.nxny.max(r.pxny)));
			System.out.println(maxDist);
		}
	}
}

