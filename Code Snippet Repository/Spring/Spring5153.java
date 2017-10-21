	@Test
	public void writeStringBuilder() {
		Flux<StringBuilder> stringBuilderFlux = Flux.just(new StringBuilder("foo"));
		Flux<DataBuffer> output = Flux.from(
				this.encoder.encode(stringBuilderFlux, this.bufferFactory, null, null, Collections.emptyMap()));
		StepVerifier.create(output)
				.consumeNextWith(stringConsumer("foo"))
				.expectComplete()
				.verify();
	}
