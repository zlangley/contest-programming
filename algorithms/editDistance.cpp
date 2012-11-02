// An implementation of Levenshtein Edit Distance (LED) using dynamic 
// programming (memoization in this case)

#include <iostream>
#include <string>
#include <vector>
#include <climits>
#include <algorithm>

using namespace std;

static const long INVALID = -1;

long editDistIndex(string a, string b, long aIndex, long bIndex, vector<vector<long> > &cache) {
	if (aIndex >= (long)a.length()) {
		// first base case (no characters left in A)
		return b.length() - bIndex;		// add characters remaining in B to A
	}
	else if (bIndex >= (long)b.length()) {
		// second base case (no characters left in B)
		return a.length() - aIndex;		// delete remaining characters in A
	}
	else if (cache[aIndex][bIndex] != INVALID) {
		// check the cache
		return cache[aIndex][bIndex];
	}
	else {
		// determine if a substitution can be used
		int diff = (a[aIndex] != b[bIndex]) ? 1 : 0;
		
		// try all three possible operations and use the best one
		long best = LONG_MAX;
		best = min(best, editDistIndex(a, b, aIndex + 1, bIndex,     cache) + 1);		// deletion from A
		best = min(best, editDistIndex(a, b, aIndex,     bIndex + 1, cache) + 1);		// insertion to A
		best = min(best, editDistIndex(a, b, aIndex + 1, bIndex + 1, cache) + diff);	// substitution in A
		cache[aIndex][bIndex] = best;
		return best;
	}
}

long editDist(string a, string b) {
	// create and initialize the cache (a 2D array)
	vector<long> tempRow(b.length(), INVALID);
	vector<vector<long> > cache(a.length(), tempRow);
	
	// compute and return the edit distance
	return editDistIndex(a, b, 0, 0, cache);
}

int main(int argc, char **argv) {
	// get input
	string a, b;
	cout << " String A: ";
	cin >> a;
	cout << " String B: ";
	cin >> b;
	
	// compute and return the result
	cout << "Edit Dist: " << editDist(a, b) << endl;
}

