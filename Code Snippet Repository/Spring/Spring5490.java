	@Test
	public void testPatternVariants() {
		doTest("*a", "*", false);
		doTest("*a", "a", true);
		doTest("*a", "b", false);
		doTest("*a", "aa", true);
		doTest("*a", "ba", true);
		doTest("*a", "ab", false);
		doTest("**a", "*", false);
		doTest("**a", "a", true);
		doTest("**a", "b", false);
		doTest("**a", "aa", true);
		doTest("**a", "ba", true);
		doTest("**a", "ab", false);
	}
