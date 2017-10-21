	@Test
	public void match() {
		MockServerHttpRequest request = MockServerHttpRequest.get("/path?param1=paramValue1").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		RequestCondition<?> condition1 = new RequestMethodsRequestCondition(RequestMethod.GET, RequestMethod.POST);
		RequestCondition<?> condition2 = new RequestMethodsRequestCondition(RequestMethod.GET);

		CompositeRequestCondition composite1 = new CompositeRequestCondition(this.param1, condition1);
		CompositeRequestCondition composite2 = new CompositeRequestCondition(this.param1, condition2);

		assertEquals(composite2, composite1.getMatchingCondition(exchange));
	}
