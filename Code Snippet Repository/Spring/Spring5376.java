	@Test
	public void cachePatternsSetToTrue() {
		pathMatcher.setCachePatterns(true);
		match();
		assertTrue(pathMatcher.stringMatcherCache.size() > 20);

		for (int i = 0; i < 65536; i++) {
			pathMatcher.match("test" + i, "test" + i);
		}
		// Cache keeps being alive due to the explicit cache setting
		assertTrue(pathMatcher.stringMatcherCache.size() > 65536);
	}
