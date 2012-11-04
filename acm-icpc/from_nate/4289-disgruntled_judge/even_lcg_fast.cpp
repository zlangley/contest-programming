#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// the modulus for the LCG: 10001 = 73*137
static const int MODULUS = 10001;

struct Solution {
	int modulus;
	int a;
	int b;
};

// Returns unique prime factors (or the input parameter n if it is prime).
// TODO: Could this be done more efficiently?
vector<int> factor(int n) {
	vector<int> factors;
	for (int i = 2; i <= n; i++) {
		bool unique = true;
		while (n % i == 0) {
			n /= i;
			if (unique) {
				factors.push_back(i);
				unique = false;
				//cout << "factor: " << i << endl;
			}
		}
	}
	return factors;
}

// A reasonably efficient application of the chinese remainder theorem
// Each pair in <partials> is a value followed by a modulus
// This assumes that the moduli are co-prime
int crt(const vector<pair<int, int> > &partials) {
	int end = 1;
	int current_max = 0;
	vector<int> current(partials.size());
	
	// initialize
	for (unsigned int i = 0; i < partials.size(); i++) {
		current[i] = partials[i].first;	// set the start of each sequence
		current_max = max(current_max, current[i]);
		end *= partials[i].second;		// end point is the product of the moduli
	}
	
	// find the first sequence intersection
	bool done = false;
	do {
		done = true;
		for (unsigned int i = 0; i < current.size(); i++) {
			if (current[i] < current_max) {
				done = false;								// no success on this round
				current[i] += partials[i].second;			// step by the modulus
				current_max = max(current_max, current[i]);	// track the current max
			}
		}
	} while (!done && current_max <= end);
	
	// all current values will be equal in the success case
	return (done ? current[0] : -1);
}

// bruteforce parameters a and b given input containing every other LCG output
Solution crack_lcg(const vector<int> &input, int modulus) {
	Solution result = {modulus, -1, -1};
	for (int a = 0; a < modulus; a++) {
		for (int b = 0; b < modulus; b++) {
			
			// check the current key pair
			for (unsigned int i=0; i < input.size(); i++) {
				
				// iterate the LCG twice
				int output_i   = (a *   input[i] + b) % modulus;
				int input_next = (a * output_i + b) % modulus;
				
				if (i+1 == input.size()) {
					// reached the end of input, success!
					result.a = a;
					result.b = b;
					return result;
				}
				else if (input_next != input[i+1] % modulus) {
					// doesn't match real input, fail out
					break;
				}
			}
		}
	}
	
	return result;	// failure if this is reached
}

int main(int argc, char **argv) {
	int cases;
	cin >> cases;	// read in the number of input cases
	
	// read the input cases
	vector<int> input(cases);
	for (int i=0; i < cases; i++) {
		cin >> input[i];
	}
	
	// nothing to do if there are no input cases
	if (cases <= 0) { return 0; }
	
	// factor the LCG modulus
	vector<int> factors = factor(MODULUS);
	
	// build partial solutions (relative to MODULUS factors)
	vector<Solution> partials(factors.size());
	for (unsigned int i = 0; i < factors.size(); i++) {
		Solution params = crack_lcg(input, factors[i]);
		partials[i] = params;
	}
	
	// combine the partial solutions to get the final solution
	vector<pair<int, int> > partials_a(partials.size());
	vector<pair<int, int> > partials_b(partials.size());
	for (unsigned int i=0; i<partials.size(); i++) {
		const Solution &p = partials[i];
		partials_a[i] = make_pair(p.a, p.modulus);
		partials_b[i] = make_pair(p.b, p.modulus);
	}
	int a = crt(partials_a);
	int b = crt(partials_b);
	//cout << "a = " << a << endl << "b = " << b << endl;
	
	// print the output for each input
	for (unsigned int i = 0; i < input.size(); i++) {
		int output = (a * input[i] + b) % MODULUS;
		cout << output << endl;
	}
	
	return 0;
}

