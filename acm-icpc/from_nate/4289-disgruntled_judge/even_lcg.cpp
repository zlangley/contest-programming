#include <iostream>
#include <vector>

using namespace std;

// the modulus for the LCG 10001 = 73*137
static const int MODULUS = 10001;
static const int A_MAX = 10000;
static const int B_MAX = 10000;

int main(int argc, char **argv) {
	int cases;
	cin >> cases;	// read in the number of input cases
	
	// read the input cases
	int *input = new int[cases];
	for (int i=0; i < cases; i++) {
		cin >> input[i];
	}
	
	// nothing to do without input cases
	if (cases <= 0) { return 0; }
	
	// crack the LCG
	int *output = new int[cases];
	bool success = false;
	for (int a = 0; a <= A_MAX && !success; a++) {
		for (int b = 0; b <= B_MAX && !success; b++) {
			// check the current key pair
			for (int i=0; i < cases; i++) {
				// iterate the LCG
				output[i]       = (a *  input[i] + b) % MODULUS;
				int input_check = (a * output[i] + b) % MODULUS;
				
				// check LCG output against the input
				if (i+1 == cases) {
					success = true;
					break;
				}
				else if (input_check != input[i+1]) {
					break;		// fail out
				}
			}
			//if (success) { cout << " a=" << a << endl << " b=" << b << endl; }
		}
	}
	
	// print the matching output
	if (success) {
		for (int i=0; i < cases; i++) {
			cout << output[i] << endl;
		}
	}
	
	// free the input buffer
	delete[] input;
	delete[] output;
	
	return 0;
}

