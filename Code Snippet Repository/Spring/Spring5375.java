	@Test
	public void defaultCacheSetting() {
		match();
		assertTrue(pathMatcher.stringMatcherCache.size() > 20);

		for (int i = 0; i < 65536; i++) {
			pathMatcher.match("test" + i, "test");
		}
		// Cache turned off because it went beyond the threshold
		assertTrue(pathMatcher.stringMatcherCache.isEmpty());
	}
