	@Test
	public void testIntegerArithmetic() {
		evaluate("2 + 4", "6", Integer.class);
		evaluate("5 - 4", "1", Integer.class);
		evaluate("3 * 5", 15, Integer.class);
		evaluate("3.2d * 5", 16.0d, Double.class);
		evaluate("3 * 5f", 15f, Float.class);
		evaluate("3 / 1", 3, Integer.class);
		evaluate("3 % 2", 1, Integer.class);
		evaluate("3 mod 2", 1, Integer.class);
		evaluate("3 mOd 2", 1, Integer.class);
		evaluate("3 Mod 2", 1, Integer.class);
		evaluate("3 MOD 2", 1, Integer.class);
	}
