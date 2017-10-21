	private void testDefaultViewName(Object returnValue, MethodParameter returnType) throws URISyntaxException {
		this.bindingContext.getModel().addAttribute("id", "123");
		HandlerResult result = new HandlerResult(new Object(), returnValue, returnType, this.bindingContext);
		ViewResolutionResultHandler handler = resultHandler(new TestViewResolver("account"));

		MockServerWebExchange exchange = MockServerWebExchange.from(get("/account").build());
		handler.handleResult(exchange, result).block(Duration.ofMillis(5000));
		assertResponseBody(exchange, "account: {id=123}");

		exchange = MockServerWebExchange.from(get("/account/").build());
		handler.handleResult(exchange, result).block(Duration.ofMillis(5000));
		assertResponseBody(exchange, "account: {id=123}");

		exchange = MockServerWebExchange.from(get("/account.123").build());
		handler.handleResult(exchange, result).block(Duration.ofMillis(5000));
		assertResponseBody(exchange, "account: {id=123}");
	}
