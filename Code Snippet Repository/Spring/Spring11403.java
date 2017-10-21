	@Test
	public void toDataBuffers() throws Exception {
		BodyExtractor<Flux<DataBuffer>, ReactiveHttpInputMessage> extractor = BodyExtractors.toDataBuffers();

		DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
		DefaultDataBuffer dataBuffer =
				factory.wrap(ByteBuffer.wrap("foo".getBytes(StandardCharsets.UTF_8)));
		Flux<DataBuffer> body = Flux.just(dataBuffer);

		MockServerHttpRequest request = MockServerHttpRequest.post("/").body(body);
		Flux<DataBuffer> result = extractor.extract(request, this.context);

		StepVerifier.create(result)
				.expectNext(dataBuffer)
				.expectComplete()
				.verify();
	}
