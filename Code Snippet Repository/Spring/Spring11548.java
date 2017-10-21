	@Test
	public void andOther() throws Exception {
		HandlerFunction<ServerResponse> handlerFunction =
				request -> ServerResponse.ok().body(fromObject("42"));
		RouterFunction<?> routerFunction1 = request -> Mono.empty();
		RouterFunction<ServerResponse> routerFunction2 =
				request -> Mono.just(handlerFunction);

		RouterFunction<?> result = routerFunction1.andOther(routerFunction2);
		assertNotNull(result);

		MockServerRequest request = MockServerRequest.builder().build();
		Mono<? extends HandlerFunction<?>> resultHandlerFunction = result.route(request);

		StepVerifier.create(resultHandlerFunction)
				.expectNextMatches(o -> o.equals(handlerFunction))
				.expectComplete()
				.verify();
	}
