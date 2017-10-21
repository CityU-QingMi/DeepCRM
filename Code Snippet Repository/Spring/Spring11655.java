	@Test
	public void compareToMultipleExpressionsAndMultipeAcceptHeaderValues() throws Exception {
		ProducesRequestCondition condition1 = new ProducesRequestCondition("text/*", "text/plain");
		ProducesRequestCondition condition2 = new ProducesRequestCondition("application/*", "application/xml");

		ServerWebExchange exchange = MockServerWebExchange.from(
				get("/").header("Accept", "text/plain", "application/xml").build());

		int result = condition1.compareTo(condition2, exchange);
		assertTrue("Invalid comparison result: " + result, result < 0);

		result = condition2.compareTo(condition1, exchange);
		assertTrue("Invalid comparison result: " + result, result > 0);

		exchange = MockServerWebExchange.from(
				MockServerHttpRequest.get("/").header("Accept", "application/xml", "text/plain").build());

		result = condition1.compareTo(condition2, exchange);
		assertTrue("Invalid comparison result: " + result, result > 0);

		result = condition2.compareTo(condition1, exchange);
		assertTrue("Invalid comparison result: " + result, result < 0);
	}
