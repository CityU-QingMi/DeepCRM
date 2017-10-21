	@Test
	public void decodeEmptyFlux() throws InterruptedException {
		Flux<DataBuffer> source = Flux.empty();
		Flux<String> output = this.decoder.decode(source, ResolvableType.forClass(String.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNextCount(0)
				.expectComplete()
				.verify();

	}
