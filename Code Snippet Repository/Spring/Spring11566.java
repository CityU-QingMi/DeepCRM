	@Test
	public void noMatch() {
		RouterFunction<ServerResponse> routerFunction = request -> Mono.empty();
		RouterFunctionMapping mapping = new RouterFunctionMapping(routerFunction);
		mapping.setMessageReaders(this.codecConfigurer.getReaders());

		Mono<Object> result = mapping.getHandler(this.exchange);

		StepVerifier.create(result)
				.expectComplete()
				.verify();
	}
