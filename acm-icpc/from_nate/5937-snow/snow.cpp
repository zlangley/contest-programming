// NOTE: UNTESTED
// No test data, other than the provided example was available at the time this
// was written.

#include <iostream>

using namespace std;

int main(int argc, char **argv) {
	
	// get base parameters
	int rows, cols, count;
	cin >> rows >> cols;
	cin >> count;
	//cout << rows << " " << cols << " " << count << endl;
	
	// return if there is no city
	if (rows <= 0 || cols <= 0) { return 0; }
	
	// build this city
	int **blueprint = new int*[rows + 1]();
	for (int r = 0; r < rows+1; r++) {
		blueprint[r] = new int[cols + 1]();
	}
	
	// let it snow
	for (int i = 0; i < count; i++) {
		// read the rectangle corners
		int r1, c1, r2, c2;
		cin >> r1 >> c1 >> r2 >> c2;
		//cout << r1 << " " << c1 << " " << r2 << " " << c2 << endl;
		r1--; c1--; r2--; c2--;		// convert to 0-indexed
		
		// sort coordinates (should not be necessary)
		int tr1 = r1;
		r1 = min(r1, r2);
		r2 = max(tr1, r2);
		int tc1 = c1;
		c1 = min(c1, c2);
		c2 = max(tc1, c2);
		
		// ignore rectangles that are off the map (also should not be necessary)
		if (r2 < 0 || c2 < 0 || r1 >= rows || c1 >= cols) { continue; }
		
		// enforce rectangle bounds (also also should not be necessary)
		r1 = max(0, min(r1, rows-1));
		r2 = max(0, min(r2, rows-1));
		c1 = max(0, min(c1, cols-1));
		c2 = max(0, min(c2, cols-1));
		
		// mark the rectangle corners
		blueprint[r1][c1]++;
		blueprint[r2+1][c1]--;
		blueprint[r1][c2+1]--;
		blueprint[r2+1][c2+1]++;
	}
	
	// convert to absolute heights
	int *scan = new int[cols+1]();	// calculated by summing along the column
	for (int r = 0; r < rows; r++) {
		int height = 0;				// calculated by summing across the row
		for (int c = 0; c < cols; c++) {
			scan[c] += blueprint[r][c];		// update the column sum
			height += scan[c];				// update the row sum
			cout << height << ((c == cols) ? "" : " ");
		}
		cout << endl;
	}

	// cleanup
	delete[] scan;
	for (int r = 0; r < rows+1; r++) {
		delete[] blueprint[r];
	}
	delete[] blueprint;
	
	return 0;
}

