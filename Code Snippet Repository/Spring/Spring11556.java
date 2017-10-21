	@Test
	public void nestNoMatch() throws Exception {
		HandlerFunction<ServerResponse> handlerFunction = request -> ServerResponse.ok().build();
		RouterFunction<ServerResponse> routerFunction = request -> Mono.just(handlerFunction);

		MockServerRequest request = MockServerRequest.builder().build();
		RequestPredicate requestPredicate = mock(RequestPredicate.class);
		when(requestPredicate.nest(request)).thenReturn(Optional.empty());

		RouterFunction<ServerResponse> result = RouterFunctions.nest(requestPredicate, routerFunction);
		assertNotNull(result);

		Mono<HandlerFunction<ServerResponse>> resultHandlerFunction = result.route(request);
		StepVerifier.create(resultHandlerFunction)
				.expectComplete()
				.verify();
	}
