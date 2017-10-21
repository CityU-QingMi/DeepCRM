	@Test
	public void decode() throws InterruptedException {
		this.decoder = StringDecoder.allMimeTypes(false);
		Flux<DataBuffer> source = Flux.just(stringBuffer("foo"), stringBuffer("bar"), stringBuffer("baz"));
		Flux<String> output = this.decoder.decode(source, ResolvableType.forClass(String.class),
				null, Collections.emptyMap());

		StepVerifier.create(output)
				.expectNext("foo", "bar", "baz")
				.expectComplete()
				.verify();

	}
