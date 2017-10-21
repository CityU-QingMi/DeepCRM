	@Test
	public void matchTrailingSlash() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo/");

		PatternsRequestCondition condition = new PatternsRequestCondition("/foo");
		PatternsRequestCondition match = condition.getMatchingCondition(request);

		assertNotNull(match);
		assertEquals("Should match by default", "/foo/", match.getPatterns().iterator().next());

		condition = new PatternsRequestCondition(new String[] {"/foo"}, null, null, false, true);
		match = condition.getMatchingCondition(request);

		assertNotNull(match);
		assertEquals("Trailing slash should be insensitive to useSuffixPatternMatch settings (SPR-6164, SPR-5636)",
				"/foo/", match.getPatterns().iterator().next());

		condition = new PatternsRequestCondition(new String[] {"/foo"}, null, null, false, false);
		match = condition.getMatchingCondition(request);

		assertNull(match);
	}
