	@Test
	public void get() throws IOException {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("http://localhost").build());
		MockServerHttpResponse mockResponse = exchange.getResponse();

		ServerRequest request = new DefaultServerRequest(exchange, HandlerStrategies.withDefaults().messageReaders());

		Mono<ServerResponse> responseMono = this.handlerFunction.handle(request);

		Mono<Void> result = responseMono.flatMap(response -> {
					assertEquals(HttpStatus.OK, response.statusCode());
					assertTrue(response instanceof EntityResponse);
					@SuppressWarnings("unchecked")
					EntityResponse<Resource> entityResponse = (EntityResponse<Resource>) response;
					assertEquals(this.resource, entityResponse.entity());
					return response.writeTo(exchange, context);
				});

		StepVerifier.create(result)
				.expectComplete()
				.verify();

		byte[] expectedBytes = Files.readAllBytes(this.resource.getFile().toPath());

		StepVerifier.create(mockResponse.getBody())
				.consumeNextWith(dataBuffer -> {
					byte[] resultBytes = new byte[dataBuffer.readableByteCount()];
					dataBuffer.read(resultBytes);
					assertArrayEquals(expectedBytes, resultBytes);
				})
				.expectComplete()
				.verify();
		assertEquals(MediaType.TEXT_PLAIN, mockResponse.getHeaders().getContentType());
		assertEquals(this.resource.contentLength(), mockResponse.getHeaders().getContentLength());
	}
