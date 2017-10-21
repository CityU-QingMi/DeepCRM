	@Test
	public void testVarargsInvocation02() {
		// Calling 'public int aVarargsMethod2(int i, String... strings)' - returns int+length_of_strings
		evaluate("aVarargsMethod2(5,'a','b','c')", 8, Integer.class);
		evaluate("aVarargsMethod2(2,'a')", 3, Integer.class);
		evaluate("aVarargsMethod2(4)", 4, Integer.class);
		evaluate("aVarargsMethod2(8,2,3)", 10, Integer.class);
		evaluate("aVarargsMethod2(9)", 9, Integer.class);
		evaluate("aVarargsMethod2(2,'a',3.0d)", 4, Integer.class);
		// evaluate("aVarargsMethod2(8,new String[]{'a','b','c'})", 11, Integer.class);
	}
