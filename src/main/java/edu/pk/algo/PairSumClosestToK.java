package edu.pk.algo;

public class PairSumClosestToK {
	public static void main(String[] args) {
		int x[] = { 3, 5, 7, 9, 14 };
		int y[] = { 1, 15, 46, 47, 54, 80, 90, 100, 110 };
		int k = 91;
		int minDiff = Math.abs(x[0] + y[y.length - 1] - k);
		int rl = 0;
		int rr = y.length - 1;
		if (x.length > 1 && y.length > 1) {
			int l = 0;
			int r = y.length - 1;
			while (l < x.length && r >= 0) {
				if (minDiff >= Math.abs(x[l] + y[r] - k)) {
					minDiff = Math.abs(x[l] + y[r] - k);
					rl = l;
					rr = r;

				} 
				/* If sum is > k then r-- else l++ */
				if (x[l] + y[r] > k) {
					r--;
				} else
					l++;
			}
		}
		System.out.println("Closet Pair to "+k+" : (" + x[rl] + "," + y[rr] + ")");
	}
}
