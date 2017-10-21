	@Test
	public void creatingStringMatchersIfPatternPrefixCannotDetermineIfPathMatch() {
		pathMatcher.setCachePatterns(true);
		assertEquals(0, pathMatcher.stringMatcherCache.size());

		pathMatcher.match("test", "testian");
		pathMatcher.match("test?", "testFf");
		pathMatcher.match("test/*", "test/dir/name.jpg");
		pathMatcher.match("test/{name}.jpg", "test/lorem.jpg");
		pathMatcher.match("bla/**/test.jpg", "bla/test.jpg");
		pathMatcher.match("**/{name}.jpg", "test/lorem.jpg");
		pathMatcher.match("/**/{name}.jpg", "/test/lorem.jpg");
		pathMatcher.match("/*/dir/{name}.jpg", "/*/dir/lorem.jpg");

		assertEquals(7, pathMatcher.stringMatcherCache.size());
	}
