	@Test
	public void testMixedOperands_DoublesAndInts() {
		evaluate("3.0d + 5", 8.0d, Double.class);
		evaluate("3.0D - 5", -2.0d, Double.class);
		evaluate("3.0f * 5", 15.0f, Float.class);
		evaluate("6.0f / 2", 3.0f, Float.class);
		evaluate("6.0f / 4", 1.5f, Float.class);
		evaluate("5.0D % 3", 2.0d, Double.class);
		evaluate("5.5D % 3", 2.5, Double.class);
	}
