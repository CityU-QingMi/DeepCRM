	@Test
	@Ignore
	public void getMatchingConditionWithCorsPreFlight() throws Exception {
		ServerWebExchange exchange = getExchange("OPTIONS");
		exchange.getRequest().getHeaders().add("Origin", "http://example.com");
		exchange.getRequest().getHeaders().add(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "PUT");

		assertNotNull(new RequestMethodsRequestCondition().getMatchingCondition(exchange));
		assertNotNull(new RequestMethodsRequestCondition(PUT).getMatchingCondition(exchange));
		assertNull(new RequestMethodsRequestCondition(DELETE).getMatchingCondition(exchange));
	}
