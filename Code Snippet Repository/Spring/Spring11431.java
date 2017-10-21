	@Test
	public void bodyParameterizedTypeReference() throws Exception {
		String body = "foo";
		Publisher<String> publisher = Mono.just(body);
		ParameterizedTypeReference<String> typeReference = new ParameterizedTypeReference<String>() {};
		ClientRequest result = ClientRequest.method(POST, URI.create("http://example.com"))
				.body(publisher, typeReference).build();

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
