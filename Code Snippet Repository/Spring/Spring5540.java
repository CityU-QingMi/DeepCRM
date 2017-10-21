	@Test
	public void testReplace() throws Exception {
		String inString = "a6AazAaa77abaa";
		String oldPattern = "aa";
		String newPattern = "foo";

		// Simple replace
		String s = StringUtils.replace(inString, oldPattern, newPattern);
		assertTrue("Replace 1 worked", s.equals("a6AazAfoo77abfoo"));

		// Non match: no change
		s = StringUtils.replace(inString, "qwoeiruqopwieurpoqwieur", newPattern);
		assertSame("Replace non-matched is returned as-is", inString, s);

		// Null new pattern: should ignore
		s = StringUtils.replace(inString, oldPattern, null);
		assertSame("Replace non-matched is returned as-is", inString, s);

		// Null old pattern: should ignore
		s = StringUtils.replace(inString, null, newPattern);
		assertSame("Replace non-matched is returned as-is", inString, s);
	}
