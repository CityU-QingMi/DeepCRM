	@Test
	public void decodeToMonoWithEmptyFlux() throws InterruptedException {
		Flux<DataBuffer> source = Flux.empty();
		Mono<String> output = this.decoder.decodeToMono(source,
				ResolvableType.forClass(String.class), null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNextCount(0)
				.expectComplete()
				.verify();
	}
