	@Test
	public void getHandlerRequestMethodMatchFalsePositive() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(get("/users").accept(MediaType.APPLICATION_XML).build());
		this.handlerMapping.registerHandler(new UserController());
		Mono<Object> mono = this.handlerMapping.getHandler(exchange);

		StepVerifier.create(mono)
				.expectError(NotAcceptableStatusException.class)
				.verify();
	}
