	@Test
	public void testVarargsInvocation01() {
		// Calling 'public int aVarargsMethod(String... strings)'
		//evaluate("aVarargsMethod('a','b','c')", 3, Integer.class);
		//evaluate("aVarargsMethod('a')", 1, Integer.class);
		evaluate("aVarargsMethod()", 0, Integer.class);
		evaluate("aVarargsMethod(1,2,3)", 3, Integer.class); // all need converting to strings
		evaluate("aVarargsMethod(1)", 1, Integer.class); // needs string conversion
		evaluate("aVarargsMethod(1,'a',3.0d)", 3, Integer.class); // first and last need conversion
		// evaluate("aVarargsMethod(new String[]{'a','b','c'})", 3, Integer.class);
	}
