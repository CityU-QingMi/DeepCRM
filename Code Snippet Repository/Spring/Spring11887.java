	@Test
	public void unresolvedViewName() throws Exception {
		String returnValue = "account";
		MethodParameter returnType = on(Handler.class).annotPresent(ModelAttribute.class).resolveReturnType(String.class);
		HandlerResult result = new HandlerResult(new Object(), returnValue, returnType, this.bindingContext);

		MockServerWebExchange exchange = MockServerWebExchange.from(get("/path").build());
		Mono<Void> mono = resultHandler().handleResult(exchange, result);

		StepVerifier.create(mono)
				.expectNextCount(0)
				.expectErrorMessage("Could not resolve view with name 'path'.")
				.verify();
	}
