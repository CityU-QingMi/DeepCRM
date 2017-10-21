	@Test
	public void testLiteralReal02_CreatingFloats() {
		// For now, everything becomes a double...
		evaluate("1.25f", 1.25f, Float.class);
		evaluate("2.5f", 2.5f, Float.class);
		evaluate("-3.5f", -3.5f, Float.class);
		evaluate("1.25F", 1.25f, Float.class);
		evaluate("2.5F", 2.5f, Float.class);
		evaluate("-3.5F", -3.5f, Float.class);
	}
