	@Test
	public void compareToMediaTypeAllWithParameter() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").header("Accept", "*/*;q=0.9").build());

		ProducesRequestCondition condition1 = new ProducesRequestCondition();
		ProducesRequestCondition condition2 = new ProducesRequestCondition("application/json");

		assertTrue(condition1.compareTo(condition2, exchange) < 0);
		assertTrue(condition2.compareTo(condition1, exchange) > 0);
	}
