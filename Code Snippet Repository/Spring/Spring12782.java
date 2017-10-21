	@Test
	public void matchSuffixPattern() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo.html");

		PatternsRequestCondition condition = new PatternsRequestCondition("/{foo}");
		PatternsRequestCondition match = condition.getMatchingCondition(request);

		assertNotNull(match);
		assertEquals("/{foo}.*", match.getPatterns().iterator().next());

		boolean useSuffixPatternMatch = false;
		condition = new PatternsRequestCondition(new String[] {"/{foo}"}, null, null, useSuffixPatternMatch, false);
		match = condition.getMatchingCondition(request);

		assertNotNull(match);
		assertEquals("/{foo}", match.getPatterns().iterator().next());
	}
