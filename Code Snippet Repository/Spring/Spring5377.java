	@Test
	public void preventCreatingStringMatchersIfPathDoesNotStartsWithPatternPrefix() {
		pathMatcher.setCachePatterns(true);
		assertEquals(0, pathMatcher.stringMatcherCache.size());

		pathMatcher.match("test?", "test");
		assertEquals(1, pathMatcher.stringMatcherCache.size());

		pathMatcher.match("test?", "best");
		pathMatcher.match("test/*", "view/test.jpg");
		pathMatcher.match("test/**/test.jpg", "view/test.jpg");
		pathMatcher.match("test/{name}.jpg", "view/test.jpg");
		assertEquals(1, pathMatcher.stringMatcherCache.size());
	}
