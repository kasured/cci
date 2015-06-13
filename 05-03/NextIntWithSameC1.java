/**
 * Given the int N count the next/previous int that has the same number of 1s
 * N should be positive
 */
class NextIntWithSameC1 {

	public static void main(String[] args) {
		final int N = Integer.parseInt(args[0], 10);
		final String Nb = Integer.toBinaryString(N);
		System.out.println("Input is " + N);
		System.out.println("Binary view is " + Nb);
		final int[] onesandzeros = onesandzeros(N);
		final int[] onesandzerosNicer = onesandzerosNicer(N);
		System.out.println("Number of 1s is " + onesandzeros[1] + " Number 0s is " + onesandzeros[0]);
		System.out.println("Nicer: Number of 1s is " + onesandzerosNicer[1] + " Number 0s is " + onesandzerosNicer[0]);
		final int nextInt = nextInt(N);
		System.out.println("Next number is " + nextInt);
		System.out.println("Binary view is " + Integer.toBinaryString(nextInt));
		final int prevInt = previousInt(N);
                System.out.println("Previous number is " + prevInt);
                System.out.println("Binary view is " + Integer.toBinaryString(prevInt));
	}

	/**
 	* Calculate the next int that has the same number of 1s.
 	* If the next such int cannot be calculated like for example for 0 or 11..1100..00 return -1
 	* @returns next greater int that has the same number of 1s or -1
 	*/ 
	private static int nextInt(int _N) {
		// Brief description
		// 1. we need to get to the position of the first non-trailing 0, i.e. we have at least one 1 to the right (position p)
		// 2. flip this bit to 1 making N bigger
		// 3. count number of 0s to the right (c0) and 1s to the right (c1)
		// 4. taking into account 2. we need to set c1 - 1 ones in the block to the right of position p

		int c0 = 0, c1 = 0;
		
		int N = _N;
		int R = _N; // R is the result
		//counting trailing zeros and preventing infinite looping for 0
		while((N & 1) == 0 && (N != 0) ) {
			c0++;
			N = N >> 1; // N should be positive otherwise >>>
		}
		
		//now caounting 1s until we hit 0 (the one we need)
		while((N & 1) == 1){
			c1++;
			N = N >> 1;
		} 	

		final int p = c0 + c1; // position p we need to flip tp 1

		//here we need to check if we have corner cases
		if(p == 31 || p == 0) return -1;

		//flip the bit to 1 
		R = R | (1 << p);
		
		// now clear the bits to the right of p
		R = R & (~((1 << p) - 1));

		// make c1 - 1 ones and merge it with the cleared R
		R = R | ( (1 << (c1 - 1)) - 1 );
		
		//we are done

		return R;
	}

	private static int previousInt(int N) {
		// 1. We need to find the first from the right non trailing 1, i.e. the 1 which has at least one 0 to the right
		// so that we can flip that zero to 1 
		// 2. After we find that position p we flip that 1 to 0 thus making the number smaller
		// 3. after that we need to reorganize c1 + 1 ones to the right to follow that p position (decrease not that much)

		int c0 = 0, c1 = 0;
		
		int _N = N;
		int R = N; // R stands for result
		//let's count trailing ones first
		while((_N & 1) == 1) {
			c1++;
			_N = _N >> 1;
		}
		
		//now counting 0s
		while((_N & 1) == 0 && _N != 0) {
			c0++;
			_N = _N >> 1;
		}
		
		final int p = c0 + c1; // our position p where we have non trailing 1, i.e. followed by zero		
		if(p == 32 || p == 0) return -1;

		// now clear all the bits to the right from p inclusive (cause we flipping it anyways)
		R = R & ((int)((~0) << (p + 1))); 
		// now make (c1 + 1) of 1s
		final int mask = (1 << (c1 + 1)) - 1;
		//and move it to the left and merge
		R = R | (mask << (c0 - 1));
		
		return R;
	}

	/**
 	* Counts number of zeros and ones for the positive integer N
 	* for negative N just make the shift >>> instead
 	* @returns array of two elements array[0] number of zeros and array[1] number of ones
 	*/
	private static int[] onesandzeros(int N) {
		final int[] counter = new int[2];
		int _N = N; 
		while(_N != 0) {
			if((_N & 1) == 1) (counter[1])++;
			_N = _N >> 1; 
		}
		counter[0] = 32 - counter[1];
		return counter;	
	}
	
	private static int[] onesandzerosNicer(int N) {
		final int[] counter = new int[2];
		counter[1] = onesIn(N, 0);
		counter[0] = 32 - counter[1];
		return counter;
	}
	private static int onesIn(int N, int c1) {
		if(N == 0) {
			return c1;
		} else {
			return onesIn(N & (N - 1), c1 + 1);
		}	
	}	
} 
