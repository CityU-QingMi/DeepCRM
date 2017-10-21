	@Test
	public void unsupportedReturnType() throws Exception {
		ByteArrayOutputStream body = new ByteArrayOutputStream();
		MethodParameter type = on(TestController.class).resolveReturnType(OutputStream.class);

		HttpMessageWriter<?> writer = new EncoderHttpMessageWriter<>(new ByteBufferEncoder());
		Mono<Void> mono = initResultHandler(writer).writeBody(body, type, this.exchange);

		StepVerifier.create(mono).expectError(IllegalStateException.class).verify();
	}
