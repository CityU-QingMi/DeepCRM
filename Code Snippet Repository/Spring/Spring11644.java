	@Test
	public void getMatchingCondition() throws Exception {
		MockServerWebExchange exchange = postExchange("text/plain");
		ConsumesRequestCondition condition = new ConsumesRequestCondition("text/plain", "application/xml");

		ConsumesRequestCondition result = condition.getMatchingCondition(exchange);
		assertConditions(result, "text/plain");

		condition = new ConsumesRequestCondition("application/xml");
		result = condition.getMatchingCondition(exchange);
		assertNull(result);
	}
