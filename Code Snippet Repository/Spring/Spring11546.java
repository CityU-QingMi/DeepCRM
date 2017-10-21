	@Test
	public void options() {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.options("http://localhost").build());
		MockServerHttpResponse mockResponse = exchange.getResponse();

		ServerRequest request = new DefaultServerRequest(exchange, HandlerStrategies.withDefaults().messageReaders());

		Mono<ServerResponse> responseMono = this.handlerFunction.handle(request);
		Mono<Void> result = responseMono.flatMap(response -> {
			assertEquals(HttpStatus.OK, response.statusCode());
			assertEquals(EnumSet.of(HttpMethod.GET, HttpMethod.HEAD, HttpMethod.OPTIONS),
					response.headers().getAllow());
			return response.writeTo(exchange, context);
		});


		StepVerifier.create(result)
				.expectComplete()
				.verify();
		assertEquals(HttpStatus.OK, mockResponse.getStatusCode());
		assertEquals(EnumSet.of(HttpMethod.GET, HttpMethod.HEAD, HttpMethod.OPTIONS),
				mockResponse.getHeaders().getAllow());

		StepVerifier.create(mockResponse.getBody()).expectComplete().verify();
	}
