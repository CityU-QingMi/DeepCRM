	@Test
	public void encode() throws Exception {
		Flux<Pojo> source = Flux.just(
				new Pojo("foo", "bar"),
				new Pojo("foofoo", "barbar"),
				new Pojo("foofoofoo", "barbarbar")
		);
		ResolvableType type = ResolvableType.forClass(Pojo.class);
		Flux<DataBuffer> output = this.encoder.encode(source, this.bufferFactory, type, null, emptyMap());

		StepVerifier.create(output)
				.consumeNextWith(stringConsumer("[{\"foo\":\"foo\",\"bar\":\"bar\"},{\"foo\":\"foofoo\",\"bar\":\"barbar\"},{\"foo\":\"foofoofoo\",\"bar\":\"barbarbar\"}]"))
				.verifyComplete();
	}
