	@Test
	public void testContainsWhitespace() throws Exception {
		assertFalse(StringUtils.containsWhitespace(null));
		assertFalse(StringUtils.containsWhitespace(""));
		assertFalse(StringUtils.containsWhitespace("a"));
		assertFalse(StringUtils.containsWhitespace("abc"));
		assertTrue(StringUtils.containsWhitespace(" "));
		assertTrue(StringUtils.containsWhitespace(" a"));
		assertTrue(StringUtils.containsWhitespace("abc "));
		assertTrue(StringUtils.containsWhitespace("a b"));
		assertTrue(StringUtils.containsWhitespace("a  b"));
	}
