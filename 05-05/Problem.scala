// Problem: Define the number of bits to convert integer A into integer B
object Problem extends App {
	val A = Integer.parseInt(args(0), 10);
	val B = Integer.parseInt(args(1), 10);
	println(s"Input: A=$A, B=$B");

	println(s"Javish: Number of bits required for convertion is ${solveItJavish(A,B)}")
	println(s"Scalish: Number of bits required for convertion is ${solveItScalish(A,B)}")

	private[this] def solveItScalish(A: Int, B: Int): Int = {
		// we make XOR to get the bits that differ and then count the number of 1s
		numOf1s(A ^ B, 0)		
	}
	@scala.annotation.tailrec
	private[this] def numOf1s(N: Int, c1: Int): Int = {
		if (N == 0) c1
		else numOf1s(N & (N - 1), c1 + 1) 
	}
	// it is too javish we can do better
	private[this] def solveItJavish(A: Int, B: Int) = {
		// we can use xor to see what bits are different
		var C = A ^ B
		var c1 = 0
		// now we can shift the number to the right and count the ones
		while(C != 0){
			if((C & 1) == 1) c1 = c1 + 1
			C = C >> 1 
		}
		c1
	}		
}
