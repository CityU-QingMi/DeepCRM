	@Test
	public void statusHandlerMatch() throws Exception {
		ClientRequest request = ClientRequest.method(GET, URI.create("http://example.com")).build();
		ClientResponse response = mock(ClientResponse.class);
		when(response.statusCode()).thenReturn(HttpStatus.NOT_FOUND);

		ExchangeFunction exchange = r -> Mono.just(response);

		ExchangeFilterFunction errorHandler = ExchangeFilterFunctions.statusError(
				HttpStatus::is4xxClientError, r -> new MyException());

		Mono<ClientResponse> result = errorHandler.filter(request, exchange);

		StepVerifier.create(result)
				.expectError(MyException.class)
				.verify();
	}
