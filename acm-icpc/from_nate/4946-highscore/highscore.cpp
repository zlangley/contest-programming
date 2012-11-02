#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main(int argc, char **argv) {
	
	// process input
	int cases;
	cin >> cases;				// read in the number of test cases
	for (int c = 0; c < cases; c++) {
		string name;
		cin >> name;
		const int len = name.length();
		
		// total numer of moves needed to change selected letters
		int changes_needed = 0;
		int updowns = 0;
		for (int i = 0; i < len; i++) {
			int updist = (int)name[i] - (int)'A';
			int downdist = 26 - updist;
			updowns += min(updist, downdist);
			
			// initialize the list of indices that require changes
			// index 0 is ignored because it's the start location
			if (updist > 0 && i != 0) {
				changes_needed++;
			}
		}
		
		// generate maps of forward and backward moves needed to change a given 
		// number of characters
		int *f_change_map = new int[changes_needed+1]();
		int *b_change_map = new int[changes_needed+1]();
		f_change_map[0] = b_change_map[0] = 0;	// no changes needed -> no moves needed
		int changes = 0;
		for (int i = 1; i < len; i++) {
			if (name[i] != 'A') {
				changes++;
				f_change_map[changes] = i;
				//cout << changes << " : " << i << endl;
			}
		}
		changes = 0;
		for (int i = len-1; i > 0; i--) {
			if (name[i] != 'A') {
				changes++;
				b_change_map[changes] = len - i;
				//cout << changes << " : " << len - i << endl;
			}
		}
		
		// choose a number of changes to make in one direction, then make
		// the remaining changes in the other direction, keep the minimum number
		// of moves needed
		int leftrights = (changes_needed > 0) ? len : 0;
		for (int i = 0; i < changes_needed; i++) {
			const int rev_changes = changes_needed - i;
			const int f_then_b = 2*f_change_map[i] + b_change_map[rev_changes];
			const int b_then_f = 2*b_change_map[i] + f_change_map[rev_changes];
			leftrights = min(leftrights, min(f_then_b, b_then_f));
		}
		
		// cleanup
		delete[] f_change_map;
		delete[] b_change_map;
		
		// NOTE
		// The strategy of always moving to the closest remaining character to
		// change does not work. Counter example:
		// AABAAAAAAABABAB
		// Nearest first would move back, then forward requiring 2*5+2=12 moves
		// The best solution is forward then back requiring 2*2+5=9 moves
		
		// print out the result
		int moves = updowns + leftrights;
		cout << moves << endl;
	}
	
	return 0;
}

