package com.aamend.dsa.numeric;


public class GreatestCommonDividor {

	public static void main(String[] args) {
		// if p>q then the gcd of p and q is the same as the gcd of p%q and q.
		System.out.println("PGCD of 15 and 9 is " + pgcd(15, 9));
	}

	public static int pgcd(int m, int n) {

		if (n == 0) {
			return m;
		}
		int t = m % n;
		
		return pgcd(n, t);
	}

}
