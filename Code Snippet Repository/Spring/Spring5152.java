	@Test
	public void writeString() {
		Flux<String> stringFlux = Flux.just("foo");
		Flux<DataBuffer> output = Flux.from(
				this.encoder.encode(stringFlux, this.bufferFactory, null, null, Collections.emptyMap()));
		StepVerifier.create(output)
				.consumeNextWith(stringConsumer("foo"))
				.expectComplete()
				.verify();
	}
