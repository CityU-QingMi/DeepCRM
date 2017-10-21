	@Test
	public void comparePatternSpecificity() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(get("/foo").build());

		PatternsRequestCondition c1 = createPatternsCondition("/fo*");
		PatternsRequestCondition c2 = createPatternsCondition("/foo");

		assertEquals(1, c1.compareTo(c2, exchange));

		c1 = createPatternsCondition("/fo*");
		c2 = createPatternsCondition("/*oo");

		assertEquals("Patterns are equally specific even if not the same", 0, c1.compareTo(c2, exchange));
	}
