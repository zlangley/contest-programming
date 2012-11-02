#include <iostream>
#include <string>

using namespace std;

// maps digits to the number of line segments (matchsticks) needed
static const int COST_MAP[] = {6,2,5,5,4,5,6,3,7,6};
static const int INPUT_MAX = 100;

// compares two strings of digits
// returns negative if the first string represents a smaller value
// returns positive if the first string represents a larger value
// returns 0 if the strings are of equal length and value
int cmp_str(const string &a, const string &b) {
	if (a.length() < b.length()) {
		return -1;
	}
	else if (a.length() > b.length()) {
		return 1;
	}
	else {
		return a.compare(b);
	}
}

const string& min_str(const string &a, const string &b) {
	// special cases for empty strings (the empty string is greater than everything)
	if (a == "") { return b; }
	else if (b == "") { return a; }
	
	// normal cases
	else if (cmp_str(a, b) <= 0) { return a; }
	else { return b; }
}

const string& max_str(const string &a, const string &b) {
	if (cmp_str(a, b) >= 0) { return a; }
	else { return b; }
}

int main(int argc, char **argv) {
	// initialize caches
	string *min_cache = new string[INPUT_MAX + 1]();
	string *max_cache = new string[INPUT_MAX + 1]();
	
	// fill the caches
	for (int i=0; i < INPUT_MAX; i++) {
		if (i == 0 || min_cache[i] != "") {
			for (int j=0; j<10; j++) {
				if (i == 0 && j == 0) { continue; }		// never start with a 0
				else if (i + COST_MAP[j] <= INPUT_MAX) {
					string &old_str = min_cache[i + COST_MAP[j]];
					string new_str = min_cache[i] + (char)(j+'0');
					old_str = min_str(old_str, new_str);
				}
			}
		}
		
		if (i == 0 || max_cache[i] != "") {
			for (int j=0; j<10; j++) {
				if (i + COST_MAP[j] <= INPUT_MAX) {
					string &old_str = max_cache[i + COST_MAP[j]];
					string new_str = max_cache[i] + (char)(j+'0');
					old_str = max_str(old_str, new_str);
				}
			}
		}
	}
	
	// process input
	int cases;
	cin >> cases;				// read in the number of test cases
	for (int i = 0; i < cases; i++) {
		int matchsticks;
		cin >> matchsticks;		// read in the number of matchsticks for this test case
		if (2 <= matchsticks && matchsticks <= INPUT_MAX) {
			cout << min_cache[matchsticks] << " " << max_cache[matchsticks] << endl;
		}
		else {
			cout << "INVALID INPUT" << endl;
		}
	}
	
	// free the caches
	delete[] max_cache;
	delete[] min_cache;
	
	return 0;
}

