	@Test
	public void toMonoVoid() throws Exception {
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
					body.complete();
				})
				.verifyComplete();
	}
