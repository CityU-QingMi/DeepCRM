	@Test
	public void toMonoWithHints() throws Exception {
		BodyExtractor<Mono<User>, ReactiveHttpInputMessage> extractor = BodyExtractors.toMono(User.class);
		this.hints.put(JSON_VIEW_HINT, SafeToDeserialize.class);

		DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
		DefaultDataBuffer dataBuffer =
				factory.wrap(ByteBuffer.wrap("{\"username\":\"foo\",\"password\":\"bar\"}".getBytes(StandardCharsets.UTF_8)));
		Flux<DataBuffer> body = Flux.just(dataBuffer);

		MockServerHttpRequest request = MockServerHttpRequest.post("/")
				.contentType(MediaType.APPLICATION_JSON)
				.body(body);

		Mono<User> result = extractor.extract(request, this.context);

		StepVerifier.create(result)
				.consumeNextWith(user -> {
					assertEquals("foo", user.getUsername());
					assertNull(user.getPassword());
				})
				.expectComplete()
				.verify();
	}
