	@Test
	public void andRoute() throws Exception {
		RouterFunction<ServerResponse> routerFunction1 = request -> Mono.empty();
		RequestPredicate requestPredicate = request -> true;

		RouterFunction<ServerResponse> result = routerFunction1.andRoute(requestPredicate, this::handlerMethod);
		assertNotNull(result);

		MockServerRequest request = MockServerRequest.builder().build();
		Mono<? extends HandlerFunction<?>> resultHandlerFunction = result.route(request);

		StepVerifier.create(resultHandlerFunction)
				.expectNextCount(1)
				.expectComplete()
				.verify();
	}
