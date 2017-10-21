	@Test
	void nullSafeToStringChecks() {
		assertEquals("null", nullSafeToString(null));
		assertEquals("", nullSafeToString(""));
		assertEquals("\t", nullSafeToString("\t"));
		assertEquals("foo", nullSafeToString("foo"));
		assertEquals("3.14", nullSafeToString(Double.valueOf("3.14")));
		assertEquals("[1, 2, 3]", nullSafeToString(new int[] { 1, 2, 3 }));
		assertEquals("[a, b, c]", nullSafeToString(new char[] { 'a', 'b', 'c' }));
		assertEquals("[foo, bar]", nullSafeToString(new String[] { "foo", "bar" }));
		assertEquals("[34, 42]", nullSafeToString(new Integer[] { 34, 42 }));
		assertEquals("[[2, 4], [3, 9]]", nullSafeToString(new Integer[][] { { 2, 4 }, { 3, 9 } }));
	}
