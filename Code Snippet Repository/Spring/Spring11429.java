	@Test
	public void bodyInserter() throws Exception {
		String body = "foo";
		BodyInserter<String, ClientHttpRequest> inserter =
				(response, strategies) -> {
					byte[] bodyBytes = body.getBytes(UTF_8);
					DataBuffer buffer = new DefaultDataBufferFactory().wrap(bodyBytes);

					return response.writeWith(Mono.just(buffer));
				};

		ClientRequest result = ClientRequest.method(POST, URI.create("http://example.com"))
				.body(inserter).build();

		List<HttpMessageWriter<?>> messageWriters = new ArrayList<>();
		messageWriters.add(new EncoderHttpMessageWriter<>(CharSequenceEncoder.allMimeTypes()));

		ExchangeStrategies strategies = mock(ExchangeStrategies.class);
		when(strategies.messageWriters()).thenReturn(messageWriters);

		MockClientHttpRequest request = new MockClientHttpRequest(GET, "/");
		result.writeTo(request, strategies).block();
		assertNotNull(request.getBody());

		StepVerifier.create(request.getBody())
				.expectNextCount(1)
				.verifyComplete();
	}
