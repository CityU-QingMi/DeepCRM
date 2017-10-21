	@Test
	public void decodeToMono() throws InterruptedException {
		this.decoder = StringDecoder.allMimeTypes(false);
		Flux<DataBuffer> source = Flux.just(stringBuffer("foo"), stringBuffer("bar"), stringBuffer("baz"));
		Mono<String> output = this.decoder.decodeToMono(source,
				ResolvableType.forClass(String.class), null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNext("foobarbaz")
				.expectComplete()
				.verify();
	}
