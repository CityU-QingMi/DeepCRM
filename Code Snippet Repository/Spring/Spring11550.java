	@Test
	public void filter() throws Exception {
		Mono<String> stringMono = Mono.just("42");
		HandlerFunction<EntityResponse<Mono<String>>> handlerFunction =
				request -> EntityResponse.fromPublisher(stringMono, String.class).build();
		RouterFunction<EntityResponse<Mono<String>>> routerFunction =
				request -> Mono.just(handlerFunction);

		HandlerFilterFunction<EntityResponse<Mono<String>>, EntityResponse<Mono<Integer>>> filterFunction =
				(request, next) -> next.handle(request).flatMap(
						response -> {
							Mono<Integer> intMono = response.entity()
									.map(Integer::parseInt);
							return EntityResponse.fromPublisher(intMono, Integer.class).build();
						});
		RouterFunction<EntityResponse<Mono<Integer>>> result = routerFunction.filter(filterFunction);
		assertNotNull(result);

		MockServerRequest request = MockServerRequest.builder().build();
		Mono<EntityResponse<Mono<Integer>>> responseMono =
				result.route(request).flatMap(hf -> hf.handle(request));

		StepVerifier.create(responseMono)
				.consumeNextWith(
						serverResponse -> {
							StepVerifier.create(serverResponse.entity())
									.expectNext(42)
									.expectComplete()
									.verify();
						})
				.expectComplete()
				.verify();
	}
