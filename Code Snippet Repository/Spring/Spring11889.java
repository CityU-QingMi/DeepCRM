	@Test
	public void contentNegotiationWith406() throws Exception {
		TestBean value = new TestBean("Joe");
		MethodParameter returnType = on(Handler.class).resolveReturnType(TestBean.class);
		HandlerResult handlerResult = new HandlerResult(new Object(), value, returnType, this.bindingContext);

		MockServerWebExchange exchange = MockServerWebExchange.from(get("/account").accept(APPLICATION_JSON).build());

		ViewResolutionResultHandler resultHandler = resultHandler(new TestViewResolver("account"));
		Mono<Void> mono = resultHandler.handleResult(exchange, handlerResult);
		StepVerifier.create(mono)
				.expectNextCount(0)
				.expectError(NotAcceptableStatusException.class)
				.verify();
	}
