import java.util.*;

class Interval implements Comparable<Interval> {
	int start;
	int end;

	public Interval() {}
	public Interval(int s, int e) {
		this.start = s;
		this.end = e;
	}

	public int compareTo(Interval i) {
		return (this.start - i.start);
	}
}

class Main {
	static final GregorianCalendar Then = new GregorianCalendar(1700, 0, 1);			// day zero
	static long MillisInDay = (1000*3600*24);
	static long MillisInHalfDay = MillisInDay/2;		// used to offset to noon to compensate for differences in DST
	
	static int dateToDay(String date) {
		int year = Integer.parseInt(date.substring(0,4));
		int month = Integer.parseInt(date.substring(4,6))-1;
		int day = Integer.parseInt(date.substring(6,8));
		GregorianCalendar now = new GregorianCalendar(year, month, day);
		long diff = (now.getTimeInMillis() - Then.getTimeInMillis() + MillisInHalfDay) / MillisInDay;
		return (int)diff;
	}

	static String dayToDate(int day) {
		GregorianCalendar now = new GregorianCalendar();
		now.setTimeInMillis(Then.getTimeInMillis() + (long)day * MillisInDay + MillisInHalfDay);
		return "" + (now.get(Calendar.MONTH)+1) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/" + now.get(Calendar.YEAR);
	}
	
	static ArrayList<Interval> rangeUnion(ArrayList<Interval> dateRanges) {
		ArrayList<Interval> union = new ArrayList<Interval>();		// output
		if (dateRanges.size() == 0) { return union; }				// nothing to do for an empty list
		
		// find the union
		Collections.sort(dateRanges);
		Interval toAdd = dateRanges.get(0);
		for (int i = 1; i < dateRanges.size(); i++) {
			if (toAdd.end + 1 >= dateRanges.get(i).start)
				toAdd.end = Math.max(dateRanges.get(i).end, toAdd.end);
			else {
				union.add(toAdd);
				toAdd = dateRanges.get(i);
			}
		}
		union.add(toAdd);
		
		return union;
	}
	
	// returns a list of intervals d = (a - b)
	static ArrayList<Interval> rangeDifference(ArrayList<Interval> a, ArrayList<Interval> b) {
		ArrayList<Interval> diff = new ArrayList<Interval>();
		
		// handle trivial cases
		if (a.size() == 0) { return diff; }
		if (b.size() == 0) { return new ArrayList<Interval>(a); }
		
		// create a list of boundary locations
		int aBounds[] = new int[a.size()*2];
		for (int i = 0; i < a.size(); i++) {
			aBounds[i*2] = a.get(i).start;
			aBounds[i*2+1] = a.get(i).end + 1;
		}
		int bBounds[] = new int[b.size()*2];
		for (int i = 0; i < b.size(); i++) {
			bBounds[i*2] = b.get(i).start;
			bBounds[i*2+1] = b.get(i).end + 1;
		}
		
		// find the difference by walking both boundary lists
		int aIndex = 0;
		int bIndex = 0;
		boolean inA = false;
		boolean inB = false;
		boolean inD = false;
		int rangeStart = 0;
		while (aIndex < aBounds.length) {		// walk until the end of A is reached
			// choose the closer of the next bounds
			int nextA = (aIndex < aBounds.length) ? aBounds[aIndex] : Integer.MAX_VALUE;
			int nextB = (bIndex < bBounds.length) ? bBounds[bIndex] : Integer.MAX_VALUE;
			if (nextA <= nextB) {
				aIndex++;
				inA = !inA;
			}
			if (nextB <= nextA) {
				bIndex++;
				inB = !inB;
			}
			boolean wasInD = inD;
			inD = (inA && !inB);
			
			// if we either went into or left the difference set
			if (inD != wasInD) {
				int day = Math.min(nextA, nextB);
				if (inD) {
					rangeStart = day;
				}
				else {
					diff.add(new Interval(rangeStart, day-1));
				}
			}
		}
		
		return diff;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int c = 0;						// case number
		while (scan.hasNext()) {
			c++;
	
			int nx = scan.nextInt();	// number of existing ranges
			int nr = scan.nextInt();	// number of requests
			
			if (nr == 0 && nx == 0) { break; }	// end of input

			// print case number and newline if needed
			if (c != 1) { System.out.println(); }
			System.out.println("Case " + c + ":");
			
			// read existing (and get the union)
			ArrayList<Interval> existing = new ArrayList<Interval>(nx);
			for (int i = 0; i < nx; i++) {
				int start = dateToDay(scan.next());
				int end = dateToDay(scan.next());
				existing.add(new Interval(start, end));
			}
			existing = rangeUnion(existing);
			
			// read requests (and get the union)
			ArrayList<Interval> requests = new ArrayList<Interval>(nr);
			for (int i = 0; i < nr; i++) {
				int start = dateToDay(scan.next());
				int end = dateToDay(scan.next());
				requests.add(new Interval(start, end));
			}
			requests = rangeUnion(requests);
			
			// find the missing sections to request
			ArrayList<Interval> diff = rangeDifference(requests, existing);
			
			if (diff.size() == 0) {
				System.out.println("    No additional quotes are required.");
			}
			else {
				for (Interval range : diff) {
					if (range.start == range.end) {
						System.out.println("    " + dayToDate(range.start));
					}
					else {
						System.out.println("    " + dayToDate(range.start) + " to " + dayToDate(range.end));
					}
				}
			}
		}
	}
}
