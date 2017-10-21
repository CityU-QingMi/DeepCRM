	@Test
	public void decodeEmptyString() throws InterruptedException {
		Flux<DataBuffer> source = Flux.just(stringBuffer(""));
		Flux<String> output = this.decoder.decode(source,
				ResolvableType.forClass(String.class), null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNext("")
				.expectComplete().verify();

	}
