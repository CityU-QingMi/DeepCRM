	@Test
	public void compareNumberOfMatchingPatterns() throws Exception {
		HttpServletRequest request = new MockHttpServletRequest("GET", "/foo.html");

		PatternsRequestCondition c1 = new PatternsRequestCondition("/foo", "*.jpeg");
		PatternsRequestCondition c2 = new PatternsRequestCondition("/foo", "*.html");

		PatternsRequestCondition match1 = c1.getMatchingCondition(request);
		PatternsRequestCondition match2 = c2.getMatchingCondition(request);

		assertEquals(1, match1.compareTo(match2, request));
	}
