	@Test
	public void compareTo() throws Exception {
		RequestMethodsRequestCondition c1 = new RequestMethodsRequestCondition(GET, HEAD);
		RequestMethodsRequestCondition c2 = new RequestMethodsRequestCondition(POST);
		RequestMethodsRequestCondition c3 = new RequestMethodsRequestCondition();

		ServerWebExchange exchange = getExchange("GET");

		int result = c1.compareTo(c2, exchange);
		assertTrue("Invalid comparison result: " + result, result < 0);

		result = c2.compareTo(c1, exchange);
		assertTrue("Invalid comparison result: " + result, result > 0);

		result = c2.compareTo(c3, exchange);
		assertTrue("Invalid comparison result: " + result, result < 0);

		result = c1.compareTo(c1, exchange);
		assertEquals("Invalid comparison result ", 0, result);
	}
