/**
 * Given int N and M insert M into N at positions from i to j
 * Example 
 * M=00000010011 
 * N=10000000000 i=2 j=6
 * Output should be
 *   10001001100 
 */
class InsertIntIntoAnother {
	public static void main(String[] args){
		final int M = Integer.parseInt(args[0], 2);
		final int N = Integer.parseInt(args[1], 2);
		final int i = Integer.parseInt(args[2], 10);
		final int j = Integer.parseInt(args[3], 10);
		
		final int result = insert(M, N, i, j);
		System.out.println(
			String.format("Inserting %s into %s from position %s to position %s will be %s",
			args[0], args[1], args[2], args[3], Integer.toBinaryString(result))
		);
		System.out.println(Integer.toString(insertBetter(M, N, i, j), 2));
	}

	private static int insert(int M, int N, int i, int j) {
		int R = N;
		for(int mth = 0, k = i; k <= j; mth++, k++) {
			final boolean isOne = (M & (1 << mth)) != 0; // get the mth bit of int M
			if(isOne) {
				R = R | (1 << k);	
			} else { // bit is not set so we just unset it from int N
				R = R & (~(1 << k));	
			}
		}
		return R;	
	}
	private static int insertBetter(int M, int N, int i, int j) {
		// 1. Clear the bits in N from i to j
		// 2. Shift M to the left to match the N, i.e. shift left by i bits		
		// 3. Merge prepared N and M by doing OR

		//prepare N clearing its bits from i to j
		final int allOnes = ~0;
		//shift it to the left so that we have 1s from j to the left
		final int maskLeft = (int)(allOnes << (j + 1));
		// now make all 1s from i to the right
		final int maskRight = (int)((1 << i) - 1);
 
		//aply this mask to clear the bits and finalize step 1
		N = N & (maskLeft | maskRight);

		// step 2
		M = M << i;

		//step 3
		N = N | M;
		
		return N; 

	}	
}
