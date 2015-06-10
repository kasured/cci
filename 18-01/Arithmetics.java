class Arithmetics {
	public static void main(String[] args) {
		final int a = Integer.parseInt(args[0], 10);
		final int b = Integer.parseInt(args[1], 10);
		System.out.println(a + "+" + b + "=" + add(a, b)); // a + b
		System.out.println("-" + a + "=" + negate(a)); // check -a == -a 
		System.out.println("-" + b + "=" + negate(b)); // check -b == -b
		System.out.println(a + "-" + b + "=" + sub(a,b)); // a - b = a + (-b)
	}
	private static int add(int a, int b) {
		if(b == 0) return a;
		final int xor = a ^ b; // do add without carrying
		final int carry = (a & b) << 1; // do carrying, no addition
		return add(xor, carry); // dive into recursion until there is nothing to carry i.e. carry = 0
	}
	private static int sub(int a, int b) {
		return add(a, negate(b)); // a - b = a + (-b) = a + negate(b) = a + ~b + 1 = add(a, add(~b, 1)) = add(add(a, ~b), 1)
	}
	private static int negate(int a) {
		return add(~a, 1); // basically negate is -i = ~i + 1 = add(~i, 1)
	}
}
