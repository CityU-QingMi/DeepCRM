	@Test
	public void bodyToMonoParameterizedTypeReference() throws Exception {
		DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
		DefaultDataBuffer dataBuffer =
				factory.wrap(ByteBuffer.wrap("foo".getBytes(StandardCharsets.UTF_8)));
		Flux<DataBuffer> body = Flux.just(dataBuffer);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_PLAIN);
		MockServerHttpRequest mockRequest = MockServerHttpRequest.method(HttpMethod.GET, "http://example.com?foo=bar").
				headers(httpHeaders).body(body);
		DefaultServerRequest request = new DefaultServerRequest(MockServerWebExchange.from(mockRequest), messageReaders);

		ParameterizedTypeReference<String> typeReference = new ParameterizedTypeReference<String>() {};
		Mono<String> resultMono = request.bodyToMono(typeReference);
		assertEquals("foo", resultMono.block());
	}
