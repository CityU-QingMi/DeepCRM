	@Test
	public void head() throws IOException {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.head("http://localhost").build());
		MockServerHttpResponse mockResponse = exchange.getResponse();

		ServerRequest request = new DefaultServerRequest(exchange, HandlerStrategies.withDefaults().messageReaders());

		Mono<ServerResponse> responseMono = this.handlerFunction.handle(request);

		Mono<Void> result = responseMono.flatMap(response -> {
			assertEquals(HttpStatus.OK, response.statusCode());
			assertTrue(response instanceof EntityResponse);
			@SuppressWarnings("unchecked")
			EntityResponse<Resource> entityResponse = (EntityResponse<Resource>) response;
			assertEquals(this.resource.getFilename(), entityResponse.entity().getFilename());
			return response.writeTo(exchange, context);
		});

		StepVerifier.create(result).expectComplete().verify();
		StepVerifier.create(mockResponse.getBody()).expectComplete().verify();

		assertEquals(MediaType.TEXT_PLAIN, mockResponse.getHeaders().getContentType());
		assertEquals(this.resource.contentLength(), mockResponse.getHeaders().getContentLength());
	}
