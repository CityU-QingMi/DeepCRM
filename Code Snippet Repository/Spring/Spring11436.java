	@Test
	public void toMonoVoidNonEmptyBody() throws Exception {
		DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
		DefaultDataBuffer dataBuffer =
				factory.wrap(ByteBuffer.wrap("foo".getBytes(StandardCharsets.UTF_8)));
		TestPublisher<DataBuffer> body = TestPublisher.create();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_PLAIN);
		when(mockResponse.getHeaders()).thenReturn(httpHeaders);
		when(mockResponse.getStatusCode()).thenReturn(HttpStatus.OK);
		when(mockResponse.getBody()).thenReturn(body.flux());

		List<HttpMessageReader<?>> messageReaders = Collections
				.singletonList(new DecoderHttpMessageReader<>(StringDecoder.allMimeTypes(true)));
		when(mockExchangeStrategies.messageReaders()).thenReturn(messageReaders);

		StepVerifier.create(defaultClientResponse.bodyToMono(Void.class))
				.then(() -> {
					body.assertWasSubscribed();
					body.emit(dataBuffer);
				})
				.verifyComplete();

		body.assertCancelled();
	}
