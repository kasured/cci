class Arithmetics {
	public static void main(String[] args) {
		final int a = Integer.parseInt(args[0], 10);
		final int b = Integer.parseInt(args[1], 10);
		System.out.println(a + "+" + b + "=" + add(a, b));
		System.out.println("-" + a + "=" + negate(a));
		System.out.println("-" + b + "=" + negate(b));
		System.out.println(a + "-" + b + "=" + sub(a,b));
	}
	private static int add(int a, int b) {
		if(b == 0) return a;
		final int xor = a ^ b;
		final int carry = (a & b) << 1;
		return add(xor, carry);
	}
	private static int sub(int a, int b) {
		return add(a, negate(b));
	}
	private static int negate(int a) {
		return add(~a, 1);
	}
}
