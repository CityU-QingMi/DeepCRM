	@Test
	public void compareNumberOfMatchingPatterns() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(get("/foo.html").build());

		PatternsRequestCondition c1 = createPatternsCondition("/foo.*", "/foo.jpeg");
		PatternsRequestCondition c2 = createPatternsCondition("/foo.*", "/foo.html");

		PatternsRequestCondition match1 = c1.getMatchingCondition(exchange);
		PatternsRequestCondition match2 = c2.getMatchingCondition(exchange);

		assertNotNull(match1);
		assertEquals(1, match1.compareTo(match2, exchange));
	}
