// NOTE: UNTESTED
// No test data, other than the provided example was available at the time this
// was written.

#include <iostream>

using namespace std;

int main(int argc, char **argv) {
	
	// generate max test input
	/*
	cout << "1000 1000" << endl;
	cout << "100000" << endl;
	for (int i=0; i<100*1000; i++) {
		cout << "1 1 1000 1000" << endl;
	}
	return 0;
	*/
	
	// get base parameters
	int rows, cols, count;
	cin >> rows >> cols;
	cin >> count;
	//cout << rows << " " << cols << " " << count << endl;
	
	// return if there is no city
	if (rows <= 0 || cols <= 0) { return 0; }
	
	// build this city
	int **city = new int*[rows + 1]();
	int **sum = new int*[rows + 1]();
	for (int r = 1; r <= rows; r++) {
		city[r] = new int[cols + 1]();
		sum[r] = new int[cols + 1]();
	}
	
	// let it snow
	for (int i = 0; i < count; i++) {
		// read the rectangle corners
		int r1, c1, r2, c2;
		cin >> r1 >> c1 >> r2 >> c2;
		//cout << r1 << " " << c1 << " " << r2 << " " << c2 << endl;
		
		// sort coordinates (should not be necessary)
		int tr1 = r1;
		r1 = min(r1, r2);
		r2 = max(tr1, r2);
		int tc1 = c1;
		c1 = min(c1, c2);
		c2 = max(tc1, c2);
		
		// ignore rectangles that are off the map (also should not be necessary)
		if (r2 < 1 || c2 < 1 || r1 > rows || c1 > cols) { continue; }
		
		// enforce rectangle bounds (also also should not be necessary)
		r1 = max(1, min(r1, rows));
		r2 = max(1, min(r2, rows));
		c1 = max(1, min(c1, cols));
		c2 = max(1, min(c2, cols));
		
		// mark the start and end of the rectangle
		if (c1 == c2) {		// rectangle width == 1 (add directly to the sum)
			for (int r = r1; r <= r2; r++) {
				sum[r][c1]++;
			}
		}
		else {
			for (int r = r1; r <= r2; r++) {
				city[r][c1]++;
				if (c2 < cols) { city[r][c2+1]--; }
			}
		}
	}
	
	// convert to absolute heights
	for (int r = 1; r <= rows; r++) {
		int height = 0;		// calculated by summing across the row
		for (int c = 1; c <= cols; c++) {
			height += city[r][c];
			sum[r][c] += height;
			cout << sum[r][c];
			if (c != cols) { cout << " "; }
		}
		cout << endl;
	}

	// cleanup
	for (int r = 1; r <= rows; r++) {
		delete[] city[r];
		delete[] sum[r];
	}
	delete[] city;
	delete[] sum;
	
	return 0;
}

