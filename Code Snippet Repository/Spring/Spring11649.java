	@Test
	public void matchTrailingSlash() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(get("/foo/").build());

		PatternsRequestCondition condition = createPatternsCondition("/foo");
		PatternsRequestCondition match = condition.getMatchingCondition(exchange);

		assertNotNull(match);
		assertEquals("Should match by default", "/foo",
				match.getPatterns().iterator().next().getPatternString());

		condition = createPatternsCondition("/foo");
		match = condition.getMatchingCondition(exchange);

		assertNotNull(match);
		assertEquals("Trailing slash should be insensitive to useSuffixPatternMatch settings (SPR-6164, SPR-5636)",
				"/foo", match.getPatterns().iterator().next().getPatternString());

		PathPatternParser parser = new PathPatternParser();
		parser.setMatchOptionalTrailingSeparator(false);
		condition = new PatternsRequestCondition(parser.parse("/foo"));
		match = condition.getMatchingCondition(MockServerWebExchange.from(get("/foo/").build()));

		assertNull(match);
	}
