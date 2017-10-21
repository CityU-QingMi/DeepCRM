	@Test
	public void toMono() throws Exception {
		BodyExtractor<Mono<String>, ReactiveHttpInputMessage> extractor = BodyExtractors.toMono(String.class);

		DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
		DefaultDataBuffer dataBuffer =
				factory.wrap(ByteBuffer.wrap("foo".getBytes(StandardCharsets.UTF_8)));
		Flux<DataBuffer> body = Flux.just(dataBuffer);

		MockServerHttpRequest request = MockServerHttpRequest.post("/").body(body);
		Mono<String> result = extractor.extract(request, this.context);

		StepVerifier.create(result)
				.expectNext("foo")
				.expectComplete()
				.verify();
	}
