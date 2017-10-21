	@Test
	public void compareToMediaTypeAll() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());

		ProducesRequestCondition condition1 = new ProducesRequestCondition();
		ProducesRequestCondition condition2 = new ProducesRequestCondition("application/json");

		assertTrue("Should have picked '*/*' condition as an exact match",
				condition1.compareTo(condition2, exchange) < 0);
		assertTrue("Should have picked '*/*' condition as an exact match",
				condition2.compareTo(condition1, exchange) > 0);

		condition1 = new ProducesRequestCondition("*/*");
		condition2 = new ProducesRequestCondition("application/json");

		assertTrue(condition1.compareTo(condition2, exchange) < 0);
		assertTrue(condition2.compareTo(condition1, exchange) > 0);

		exchange = MockServerWebExchange.from(
				MockServerHttpRequest.get("/").header("Accept", "*/*").build());

		condition1 = new ProducesRequestCondition();
		condition2 = new ProducesRequestCondition("application/json");

		assertTrue(condition1.compareTo(condition2, exchange) < 0);
		assertTrue(condition2.compareTo(condition1, exchange) > 0);

		condition1 = new ProducesRequestCondition("*/*");
		condition2 = new ProducesRequestCondition("application/json");

		assertTrue(condition1.compareTo(condition2, exchange) < 0);
		assertTrue(condition2.compareTo(condition1, exchange) > 0);
	}
