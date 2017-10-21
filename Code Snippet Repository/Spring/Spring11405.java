	@Test
	public void toMonoParameterizedTypeReference() throws Exception {
		ParameterizedTypeReference<Map<String, String>> typeReference = new ParameterizedTypeReference<Map<String, String>>() {};
		BodyExtractor<Mono<Map<String, String>>, ReactiveHttpInputMessage> extractor = BodyExtractors.toMono(typeReference);

		DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
		DefaultDataBuffer dataBuffer =
				factory.wrap(ByteBuffer.wrap("{\"username\":\"foo\",\"password\":\"bar\"}".getBytes(StandardCharsets.UTF_8)));
		Flux<DataBuffer> body = Flux.just(dataBuffer);

		MockServerHttpRequest request = MockServerHttpRequest.post("/").contentType(MediaType.APPLICATION_JSON).body(body);
		Mono<Map<String, String>> result = extractor.extract(request, this.context);

		Map<String, String > expected = new LinkedHashMap<>();
		expected.put("username", "foo");
		expected.put("password", "bar");
		StepVerifier.create(result)
				.expectNext(expected)
				.expectComplete()
				.verify();
	}
