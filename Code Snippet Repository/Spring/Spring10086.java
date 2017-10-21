	@Test
	public void encodeWithType() throws Exception {
		Flux<ParentClass> source = Flux.just(new Foo(), new Bar());
		ResolvableType type = ResolvableType.forClass(ParentClass.class);
		Flux<DataBuffer> output = this.encoder.encode(source, this.bufferFactory, type, null, emptyMap());

		StepVerifier.create(output)
				.consumeNextWith(stringConsumer("[{\"type\":\"foo\"},{\"type\":\"bar\"}]"))
				.verifyComplete();
	}
