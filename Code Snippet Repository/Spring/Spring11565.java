	@Test
	public void normal() {
		HandlerFunction<ServerResponse> handlerFunction = request -> ServerResponse.ok().build();
		RouterFunction<ServerResponse> routerFunction = request -> Mono.just(handlerFunction);

		RouterFunctionMapping mapping = new RouterFunctionMapping(routerFunction);
		mapping.setMessageReaders(this.codecConfigurer.getReaders());

		Mono<Object> result = mapping.getHandler(this.exchange);

		StepVerifier.create(result)
				.expectNext(handlerFunction)
				.expectComplete()
				.verify();
	}
