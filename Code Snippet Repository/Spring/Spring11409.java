	@Test
	public void toFluxWithHints() throws Exception {
		BodyExtractor<Flux<User>, ReactiveHttpInputMessage> extractor = BodyExtractors.toFlux(User.class);
		this.hints.put(JSON_VIEW_HINT, SafeToDeserialize.class);

		DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
		DefaultDataBuffer dataBuffer =
				factory.wrap(ByteBuffer.wrap("[{\"username\":\"foo\",\"password\":\"bar\"},{\"username\":\"bar\",\"password\":\"baz\"}]".getBytes(StandardCharsets.UTF_8)));
		Flux<DataBuffer> body = Flux.just(dataBuffer);

		MockServerHttpRequest request = MockServerHttpRequest.post("/")
				.contentType(MediaType.APPLICATION_JSON)
				.body(body);

		Flux<User> result = extractor.extract(request, this.context);

		StepVerifier.create(result)
				.consumeNextWith(user -> {
					assertEquals("foo", user.getUsername());
					assertNull(user.getPassword());
				})
				.consumeNextWith(user -> {
					assertEquals("bar", user.getUsername());
					assertNull(user.getPassword());
				})
				.expectComplete()
				.verify();
	}
